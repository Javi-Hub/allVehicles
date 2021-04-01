package com.sanvalero.allVehiclesAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 01/04/2021
 */
@Data
@NoArgsConstructor
public class CompanyDTO {

    @Schema(description = "Company name", example = "Daimler", required = true)
    private String name;

    @Schema(description = "Headquarters", example = "Sttutgart")
    private String headquarter;

    @Schema(description = "Country", example = "Germany")
    private String country;

    @Schema(description = "Number of employees", example = "5000")
    private int employees;

    @Schema(description = "listing on the stock market", example = "false")
    private boolean stockMarket;

    @Schema(description = "Percentage shares for sale on the stock exchange ", example = "12.4")
    private float sharesStock;

    @Schema(description = "Foundation date", example = "12/05/1920")
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate foundationDate;
}
