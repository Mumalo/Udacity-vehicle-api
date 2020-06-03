package com.udacity.vehicles.domain.car;

import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.domain.manufacturer.ManufacturerDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Setter @Getter @ToString
public class DetailsDTO {
    private String body;

    private String model;

    private ManufacturerDTO manufacturerDTO;

    private Integer numberOfDoors;

    private String fuelType;

    private String engine;

    private Integer mileage;

    private Integer modelYear;

    private Integer productionYear;

    private String externalColor;

    public static DetailsDTO getInstance(String body, String model,ManufacturerDTO manufacturerDTO, String fuelType, String engine,
    Integer mileage, Integer modelYear, Integer productionYear, String externalColor){
        return new DetailsDTO()
                .setBody(body)
                .setModel(model)
                .setManufacturerDTO(manufacturerDTO)
                .setFuelType(fuelType)
                .setEngine(engine)
                .setMileage(mileage)
                .setModelYear(modelYear)
                .setProductionYear(productionYear)
                .setExternalColor(externalColor);
    }
}
