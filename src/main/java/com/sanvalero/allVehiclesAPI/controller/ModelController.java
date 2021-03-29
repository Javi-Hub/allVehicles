package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.ModelNotFoundException;
import com.sanvalero.allVehiclesAPI.service.model.ModelService;
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
@Tag(name = "Models", description = "Models Information")
public class ModelController {

    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    //****************************** POST ********************************
    @Operation(summary = "Insert a new model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Model registered", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert model", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/allVehicles/brand/{id}/model", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Model> addModel(@PathVariable long id, @RequestBody ModelDTO modelDTO){
        logger.info("[init addModel]");
        Model addedModel = modelService.addModel(id, modelDTO);
        logger.info("[end addModel]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedModel);
    }

    //****************************** EXCEPTION ********************************
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ModelNotFoundException mnfe){
        Response response = Response.errorResponse(NOT_FOUND, mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
