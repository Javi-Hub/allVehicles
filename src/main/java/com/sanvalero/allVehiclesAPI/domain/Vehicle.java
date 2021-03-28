package com.sanvalero.allVehiclesAPI.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 28/03/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vehicle")
public class Vehicle {

    @Schema(description = "Vehicle ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Vehicle name", example = "200D", required = true)
    @Column(name = "vehicle")
    private String name;

    @Schema(description = "Horse power", example = "150")
    @Column(name = "horse_power")
    private int horsePower;

    @Schema(description = "Eco Label", example = "true")
    @Column(name = "eco_label")
    private boolean ecoLabel;

    @Schema(description = "Fuel consumption", example = "5.8")
    @Column
    private float consumption;

    @Schema(description = "Market launch", example = "15/12/2005")
    @Column(name = "market_launch")
    private LocalDate marketLaunch;
}
