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
@Entity(name = "brand")
public class Brand {

    @Schema(description = "Brand ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Brand name", example = "Mercedes Benz", required = true)
    @NotBlank
    @Column(name = "brand")
    private String name;

    @Schema(description = "Ranking position", example = "23")
    @Column
    private int ranking;

    @Schema(description = "Currently running", example = "true")
    @NotBlank
    @Column
    private boolean operational;

    @Schema(description = "Valuation of the people", example = "7.8")
    @Column
    private float assessment;

    @Schema(description = "Foundation date", example = "12/05/1920")
    @JsonFormat(pattern = "dd/MM/yyy")
    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "brand")
    private List<Model> modelList;

}
