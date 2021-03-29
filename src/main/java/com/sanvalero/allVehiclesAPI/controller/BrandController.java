package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.service.Brand.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sanvalero.allVehiclesAPI.controller.Response.NOT_FOUND;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@RestController
@Tag(name = "Brands", description = "Brands Information")
public class BrandController {

    private final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    //****************************** POST ********************************
    @Operation(summary = "Insert a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand registered", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert brand", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "allVehicles/company/{id}/brand", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Brand> addBrand(@PathVariable long id, @RequestBody BrandDTO brandDTO){
        logger.info("[init addBrand]");
        Brand addedBrand = brandService.addBrand(id, brandDTO);
        logger.info("[end addBrand]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBrand);
    }


    //****************************** EXCEPTION ********************************
    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BrandNotFoundException bnfe){
        Response response = Response.errorResponse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
