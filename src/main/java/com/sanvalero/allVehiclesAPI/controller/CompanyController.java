package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.service.Company.CompanyService;
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
    @PostMapping(value = "allVehicles/company", produces = "application/json", consumes = "application/json")
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
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "allVehicles/company", produces = "application/json")
    public ResponseEntity<Set<Company>> getAllCompanies(){
        logger.info("[init getAllCompnies]");
        Set<Company> companies = companyService.findCompanies();
        logger.info("[end getAllCompnies]");
        return ResponseEntity.status(HttpStatus.OK).body(companies);
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
