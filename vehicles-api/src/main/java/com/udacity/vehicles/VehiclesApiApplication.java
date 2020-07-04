package com.udacity.vehicles;

import com.sun.jndi.toolkit.url.Uri;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.domain.manufacturer.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

/**
 * Launches a Spring Boot application for the Vehicles API,
 * initializes the car manufacturers in the database,
 * and launches web clients to communicate with maps and pricing.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaAuditing
public class VehiclesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesApiApplication.class, args);
    }
    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * Initializes the car manufacturers available to the Vehicle API.
     * @param repository where the manufacturer information persists.
     * @return the car manufacturers to add to the related repository
     */
    @Bean
    CommandLineRunner initDatabase(ManufacturerRepository repository) {
        return args -> {
            repository.save(new Manufacturer(100, "Audi"));
            repository.save(new Manufacturer(101, "Chevrolet"));
            repository.save(new Manufacturer(102, "Ford"));
            repository.save(new Manufacturer(103, "BMW"));
            repository.save(new Manufacturer(104, "Dodge"));
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Web Client for the maps (location) API
     * @param serviceId where to communicate for the maps API
     * @return created maps endpoint
     */
    @LoadBalanced
    @Bean(name="maps")
    public WebClient webClientMaps(@Value("${maps.serviceId}") String serviceId) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(serviceId).get(0);
        URI uri = serviceInstance.getUri();
        System.out.printf("Maps url is %s", uri.toString());
        return WebClient.create(uri.toString());
    }

    /**
     * Web Client for the pricing API
     * @param serviceId where to communicate for the pricing API
     * @return created pricing endpoint
     */
    @LoadBalanced
    @Bean(name="pricing")
    public WebClient webClientPricing(@Value("${pricing.serviceId}") String serviceId) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(serviceId).get(0);
        URI uri = serviceInstance.getUri();
        System.out.printf("%n Pricing url is %s%n", uri.toString());
        return WebClient.create(uri.toString());
    }

}
