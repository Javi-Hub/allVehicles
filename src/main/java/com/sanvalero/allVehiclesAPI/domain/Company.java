package com.sanvalero.allVehiclesAPI.domain;

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
@Entity(name = "company")
public class Company {

    @Schema(description = "Company ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Company name", example = "Daimler", required = true)
    @NotBlank
    @Column(name = "company")
    private String name;

    @Schema(description = "Headquarters", example = "Sttutgart")
    @NotBlank
    @Column
    private String headquarter;

    @Schema(description = "Country", example = "Germany")
    @NotBlank
    @Column
    private String country;

    @Schema(description = "Number of employees", example = "5000")
    @NotBlank
    @Column
    private int employees;

    @Schema(description = "listing on the stock market", example = "false")
    @NotBlank
    @Column(name = "stock_market")
    private boolean stockMarket;

    @Schema(description = "Percentage shares for sale on the stock exchange ", example = "12.4")
    @Column
    private float sharesStock;

    @Schema(description = "Foundation date", example = "12/05/1920")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Brand> brandList;

}
