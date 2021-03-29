package com.sanvalero.allVehiclesAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Data
@NoArgsConstructor
public class ModelDTO {

    @Schema(description = "Model of the vehicle", example = "GLA", required = true)
    private String name;

    @Schema(description = "Type of vehicle", example = "SUV")
    private String type;

    @Schema(description = "Units manufactured", example = "450000")
    private int units;

    @Schema(description = "The vehícle is available for sale", example = "true")
    private boolean available;

    @Schema(description = "Length of the vehicule", example = "4.69")
    private float length;

    @Schema(description = "Market launch", example = "10/10/1978")
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate marketLaunch;

}
