package com.sanvalero.allVehiclesAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Data
@NoArgsConstructor
public class BrandDTO {

    @Schema(description = "Brand name", example = "Mercedes Benz", required = true)
    private String name;

    @Schema(description = "Ranking position", example = "23")
    private int ranking;

    @Schema(description = "Currently running", example = "true")
    private boolean operational;

    @Schema(description = "Valuation of the people", example = "7.8")
    private float assessment;

    @Schema(description = "Foundation date", example = "12/05/1920")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

}
