package com.udacity.vehicles.domain.car;

import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter @Getter @NoArgsConstructor
@Accessors(chain = true)
public class CarDTO {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Condition condition;

    private DetailsDTO details = new DetailsDTO();

    private Location location = new Location(0d, 0d);

    private String price;

    public static CarDTO getInstance(LocalDateTime createdAt, LocalDateTime modifiedAt, Condition condition, DetailsDTO details){
        return new CarDTO();
    }
}
