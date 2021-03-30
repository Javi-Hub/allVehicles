package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Vehicle;
import com.sanvalero.allVehiclesAPI.domain.dto.VehicleDTO;
import com.sanvalero.allVehiclesAPI.service.vehicle.VehicleService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creado por @author: Javier
 * el 30/03/2021
 */
@RestController
@Tag(name = "vehicles", description = "Vehicles Information")
public class VehicleController {

    private final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    //****************************** POST ********************************
    @Operation(summary = "Insert a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle registered", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert vehicle", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/allVehicles/model/{id}/vehicle", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Vehicle> addVehicle(@PathVariable long id, @RequestBody VehicleDTO vehicleDTO){
        logger.info("[init addVehicle]");
        Vehicle addedVehicle = vehicleService.addVehicle(id, vehicleDTO);
        logger.info("[end addVehicle]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedVehicle);
    }



}
