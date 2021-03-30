package com.sanvalero.allVehiclesAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 30/03/2021
 */
@Data
@NoArgsConstructor
public class VehicleDTO {

    @Schema(description = "Vehicle name", example = "200D", required = true)
    private String name;

    @Schema(description = "Horse power (CV)", example = "150")
    private int horsePower;

    @Schema(description = "Eco Label", example = "true")
    private boolean ecoLabel;

    @Schema(description = "Price of the vehicle", example = "35500.45")
    private float price;

    @Schema(description = "Fuel consumption", example = "5.8")
    private float consumption;

    @Schema(description = "Market launch", example = "15/12/2005")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate marketLaunch;

}
