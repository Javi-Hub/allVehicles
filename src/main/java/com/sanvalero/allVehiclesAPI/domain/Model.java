package com.sanvalero.allVehiclesAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @author: Javier
 * el 28/03/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "model")
public class Model {

    @Schema(description = "Model ID", example = "3", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Model of the vehicle", example = "GLA", required = true)
    @NotBlank
    @Column(name = "model")
    private String name;

    @Schema(description = "Type of vehicle", example = "SUV")
    @NotBlank
    @Column
    private String type;

    @Schema(description = "Units manufactured", example = "450000")
    @Column
    private int units;

    @Schema(description = "The vehícle is available for sale", example = "true")
    @NotBlank
    @Column
    private boolean available;

    @Schema(description = "Length of the vehicule", example = "4.69")
    @Column
    private float length;

    @Schema(description = "Market launch", example = "10/10/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "market_launch")
    private LocalDate marketLaunch;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE)
    private List<Vehicle> vehicleList;

}
