package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.service.Company.CompanyService;
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
@Tag(name = "Companies", description = "Companies Information")
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    //****************************** POST ********************************
    @Operation(summary = "Insert a new company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company registered", content = @Content(schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert company", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/allVehicles/company", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        logger.info("[init addCompany]");
        Company addedCompany = companyService.addCompany(company);
        logger.info("[end addCompany]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCompany);
    }

    //****************************** GET ********************************

    @Operation(summary = "Get all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all vehicle companies", content = @Content(schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allVehicles/company", produces = "application/json")
    public ResponseEntity<Set<Company>> getAllCompanies(){
        logger.info("[init getAllCompanies]");
        Set<Company> companies = companyService.findCompanies();
        logger.info("[end getAllCompanies]");
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    //****************************** PUT ********************************
    @Operation(summary = "Modify company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a company", content = @Content(schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify company", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/allVehicles/company/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Company> modifyCompany(@PathVariable long id, @RequestBody Company newCompany){
        logger.info("[init modifyCompany]");
        Company company = companyService.modifyCompany(id, newCompany);
        logger.info("[end modifyCompany]");
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    //****************************** PATCH ********************************
    @Operation(summary = "Modify sharesStock Company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field shares stock of a company", content = @Content(schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify shares stock", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/allVehicles/company/{id}/change-sharesStock", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Company> modifyCompanyBySharesStock(@PathVariable long id,
                                                     @RequestParam(value = "sharesStock", defaultValue = "") float sharesStock){
        logger.info("[init modifyCompanyBySharesStock]");
        Company company = companyService.modifyCompanyBySharesStock(id, sharesStock);
        logger.info("[end modifyCompanyBySharesStock]");
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    //****************************** DELETE ********************************
    @Operation(summary = "Delete company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a company", content = @Content(schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting company", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/allVehicles/company/{id}")
    public ResponseEntity<Response> deleteCompany(@PathVariable long id){
        logger.info("[init deleteCompany]");
        companyService.deleteCompany(id);
        logger.info("[end deleteCompany]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
    }


    //****************************** EXCEPTION ********************************
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(CompanyNotFoundException cnfe){
        Response response = Response.errorResponse(NOT_FOUND, cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
