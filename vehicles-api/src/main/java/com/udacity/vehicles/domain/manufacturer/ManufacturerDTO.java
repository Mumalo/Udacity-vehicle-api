package com.udacity.vehicles.domain.manufacturer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter
@Accessors(chain = true)
@NoArgsConstructor
public class ManufacturerDTO {

    private String name;
    private Integer code;

    public static ManufacturerDTO getInstance(Integer code, String name) {
        return new ManufacturerDTO()
                .setName(name)
                .setCode(code);
    }
}
