package com.sanvalero.allVehiclesAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @Schema(description = "Horse power (CV)", example = "150")
    @Column(name = "horse_power")
    private int horsePower;

    @Schema(description = "Eco Label", example = "true")
    @Column(name = "eco_label")
    private boolean ecoLabel;

    @Schema(description = "Price of the vehicle", example = "35500.45")
    @Column
    private float price;

    @Schema(description = "Fuel consumption", example = "5.8")
    @Column
    private float consumption;

    @Schema(description = "Market launch", example = "15/12/2005")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "market_launch")
    private LocalDate marketLaunch;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference
    private Model model;

}
