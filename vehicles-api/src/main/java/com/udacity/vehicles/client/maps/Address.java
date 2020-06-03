package com.udacity.vehicles.client.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Declares a class to store an address, city, state and zip code.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @NoArgsConstructor
public class Address {

    private String address;
    private String city;
    private String state;
    private String zip;
}
