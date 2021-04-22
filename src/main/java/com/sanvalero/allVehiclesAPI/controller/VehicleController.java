package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.Vehicle;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;
import com.sanvalero.allVehiclesAPI.domain.dto.VehicleDTO;
import com.sanvalero.allVehiclesAPI.exception.ModelNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.VehicleNotFoundException;
import com.sanvalero.allVehiclesAPI.service.vehicle.VehicleService;
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
    @PostMapping(value = "/allvehicles/models/{id}/vehicles", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Vehicle> addVehicle(@PathVariable long id, @RequestBody VehicleDTO vehicleDTO){
        logger.info("[init addVehicle]");
        Vehicle addedVehicle = vehicleService.addVehicle(id, vehicleDTO);
        logger.info("[end addVehicle]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedVehicle);
    }

    //****************************** GET ********************************
    @Operation(summary = "Get all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all vehicles", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allvehicles/vehicles", produces = "application/json")
    public ResponseEntity<Set<Vehicle>> getAllVehicles(){
        logger.info("[init getAllVehicles]");
        Set<Vehicle> vehicles = vehicleService.findVehicles();
        logger.info("[end getAllVehicles]");
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @Operation(summary = "Get vehicle by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of a vehicle", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/allvehicles/vehicles/{id}", produces = "application/json")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id){
        logger.info("[init getVehicleById]");
        Vehicle vehicle = vehicleService.findVehicleById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        logger.info("[end getVehicleById]");
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    //****************************** PUT ********************************
    @Operation(summary = "Modify vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a vehicle", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify vehicle", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/allvehicles/vehicles/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Vehicle> modifyVehicle(@PathVariable long id,
                                                 @RequestBody VehicleDTO vehicleDTO){
        logger.info("[init modifyVehicle]");
        Vehicle vehicle = vehicleService.modifyVehicle(id, vehicleDTO);
        logger.info("[end modifyVehicle]");
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    //****************************** PATCH ********************************
    @Operation(summary = "Modify price vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field price vehicle", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify price vehicle", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/allvehicles/vehicles/{id}/change-price", produces = "application/json")
    public ResponseEntity<Vehicle> modifyVehicleByPrice(@PathVariable long id,
                                                        @RequestParam(value = "price", defaultValue = "") float price){
        logger.info("[init modifyVehicleByPrice]");
        Vehicle vehicle = vehicleService.modifyVehicleByPrice(id, price);
        logger.info("[end modifyVehicleByPrice]");
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    //****************************** DELETE ********************************
    @Operation(summary = "Delete vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a vehicle", content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting vehicle", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/allvehicles/vehicles/{id}")
    public ResponseEntity<Response> deleteVehicle(@PathVariable long id){
        logger.info("[init deleteVehicle]");
        vehicleService.deleteVehicle(id);
        logger.info("[end deleteVehicle]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
    }

    //****************************** EXCEPTION ********************************
    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(VehicleNotFoundException vnfe){
        Response response = Response.errorResponse(NOT_FOUND, vnfe.getMessage());
        logger.error(vnfe.getMessage(), vnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
