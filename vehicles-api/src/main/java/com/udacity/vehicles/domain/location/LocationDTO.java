package com.udacity.vehicles.domain.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class LocationDTO {
    private Double lat;

    private Double lon;

    private String address;

    private String city;

    private String state;

    private String zip;

    public static LocationDTO getInstance(Double lat, Double lon, String address, String city, String state, String zip){
        return new LocationDTO()
                .setLat(lat)
                .setLon(lon)
                .setAddress(address)
                .setCity(city)
                .setState(state)
                .setZip(zip);
    }

}
