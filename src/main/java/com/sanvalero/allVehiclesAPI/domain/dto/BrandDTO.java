package com.sanvalero.allVehiclesAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String name;
    private int ranking;
    private boolean operational;
    private float assessment;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

}
