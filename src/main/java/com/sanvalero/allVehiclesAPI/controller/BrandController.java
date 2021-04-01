package com.sanvalero.allVehiclesAPI.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.service.brand.BrandService;
import com.sun.source.doctree.AttributeTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.Set;

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
    @PostMapping(value = "/allVehicles/company/{id}/brand", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Brand> addBrand(@PathVariable long id, @RequestBody BrandDTO brandDTO){
        logger.info("[init addBrand]");
        Brand addedBrand = brandService.addBrand(id, brandDTO);
        logger.info("[end addBrand]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBrand);
    }

    //****************************** GET ********************************
    @Operation(summary = "Get all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all vehicle brands", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allVehicles/brand", produces = "application/json")
    public ResponseEntity<Set<Brand>> getAllBrands(){
        logger.info("[init getAllBrands]");
        Set<Brand> brands = brandService.findBrands();
        logger.info("[end getAllBrands]");
        return ResponseEntity.status(HttpStatus.OK).body(brands);
    }

    @Operation(summary = "Get brand by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get brand by name", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allVehicles/brand/name", produces = "application/json")
    public ResponseEntity<Brand> getBrandByName(@RequestParam(value = "name", defaultValue = "") String name){
        logger.info("[init getBrandByName]");
        Brand brand = brandService.findByName(name);
        logger.info("[end getBrandByName]");
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    //****************************** PUT ********************************
    @Operation(summary = "Modify brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a brand", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify brand", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/allVehicles/brand/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Brand> modifyBrand(@PathVariable long id,
                                             @RequestBody BrandDTO brandDTO){
        logger.info("[init modifyBrand]");
        Brand brand = brandService.modifyBrand(id, brandDTO);
        logger.info("[end modifyBrand]");
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    //****************************** PATCH ********************************
    @Operation(summary = "Modify assessment Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field assessment of a brand", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify assessment", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/allVehicles/brand/{id}/change-assessment", produces = "application/json")
    public ResponseEntity<Brand> modifyBrandAssessment(@PathVariable long id,
                                                       @RequestParam(value = "assessment", defaultValue = "") float assessment){
        logger.info("[init modifyBrandByAssessment]");
        Brand brand = brandService.modifyBrandByAssessment(id, assessment);
        logger.info("[end modifyBrandByAssessment]");
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    //****************************** DELETE ********************************
    @Operation(summary = "Delete brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a brand", content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting brand", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/allVehicles/brand/{id}")
    public ResponseEntity<Response> deleteBrand(@PathVariable long id){
        logger.info("[init deleteBrand]");
        brandService.deleteBrand(id);
        logger.info("[end deleteBrand]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
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
