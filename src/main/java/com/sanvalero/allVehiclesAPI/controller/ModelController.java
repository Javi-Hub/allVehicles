package com.sanvalero.allVehiclesAPI.controller;

import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;
import com.sanvalero.allVehiclesAPI.exception.ModelNotFoundException;
import com.sanvalero.allVehiclesAPI.service.model.ModelService;
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
    @PostMapping(value = "/allvehicles/brands/{id}/models", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Model> addModel(@PathVariable long id, @RequestBody ModelDTO modelDTO){
        logger.info("[init addModel]");
        Model addedModel = modelService.addModel(id, modelDTO);
        logger.info("[end addModel]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedModel);
    }

    //****************************** GET ********************************
    @Operation(summary = "Get all models")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all vehicle models", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allvehicles/models", produces = "application/json")
    public ResponseEntity<Set<Model>> getAllModels(){
        logger.info("[init getAllModels]");
        Set<Model> models = modelService.findModels();
        logger.info("[end getAllModels]");
        return ResponseEntity.status(HttpStatus.OK).body(models);
    }

    @Operation(summary = "Get model by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of a model", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/allvehicles/models/{id}", produces = "application/json")
    public ResponseEntity<Model> getModelById(@PathVariable long id){
        logger.info("[init getModelById]");
        Model model = modelService.findModelById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        logger.info("[end getModelById]");
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    @Operation(summary = "Get models filtered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get models filtered by name, type and available", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(array = @ArraySchema(schema= @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/allvehicles/models/name-type-available", produces = "application/json")
    public ResponseEntity<Set<Model>> getModelsFilteredByNameTypeAvailable
                                    (@RequestParam(value = "name", defaultValue = "") String name,
                                     @RequestParam(value = "type", defaultValue = "") String type,
                                     @RequestParam(value = "available", defaultValue = "") boolean available){
        logger.info("[init getModelsFilteredByNameTypeAvailable]");
        Set<Model> models = modelService.findByNameAndTypeAndAvailable(name, type, available);
        logger.info("[end getModelsFilteredByNameTypeAvailable]");
        return ResponseEntity.status(HttpStatus.OK).body(models);
    }

    @Operation(summary = "Get models filtered by company, brand and units model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get models filtered by company, brand and units model", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Model.class)))),
            @ApiResponse(responseCode = "404", description = "Fail get models", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/allvehicles/brand-model-units-type")
    public ResponseEntity<Set<Model>> getModelsFilteredByCompanyBrandAndUnits(@RequestParam(value = "brand", defaultValue = "") String brand,
                                                                              @RequestParam(value = "maxUnit", defaultValue = "") int maxUnit,
                                                                              @RequestParam(value = "minUnit", defaultValue = "") int minUnit,
                                                                              @RequestParam(value = "type", defaultValue = "") String type){
        logger.info("[getModelsFilteredByCompanyBrandAndUnits]");
        Set<Model> models = modelService.findByCompanyAndBrandAndUnitsmodel(brand, type, maxUnit, minUnit);
        logger.info("[getModelsFilteredByCompanyBrandAndUnits]");
        return ResponseEntity.status(HttpStatus.OK).body(models);
    }

    //****************************** PUT ********************************
    @Operation(summary = "Modify model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a model", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify model", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/allvehicles/models/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Model> modifyModel(@PathVariable long id, @RequestBody ModelDTO modelDTO){
        logger.info("[init modifyModel]");
        Model model =  modelService.modifyModel(id, modelDTO);
        logger.info("[end modifyModel]");
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    //****************************** PATCH ********************************
    @Operation(summary = "Modify model by units")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field units of a model", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify units model", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/allvehicles/models/{id}/change-units", produces = "application/json")
    public ResponseEntity<Model> modifyModelByUnits(@PathVariable long id,
                                                    @RequestParam(value = "units", defaultValue = "") int units){
        logger.info("[init modifyModelByUnits]");
        Model model = modelService.modifyModelByUnits(id, units);
        logger.info("[end modifyModelByUnits]");
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    @Operation(summary = "Modify model by length")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field length of a model", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify length model", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/allvehicles/models/{id}/change-length", produces = "application/json")
    public ResponseEntity<Model> modifyModelByLength(@PathVariable long id,
                                                        @RequestParam(value = "length", defaultValue = "") float length){
        logger.info("[init modifyModelByLength]");
        Model model = modelService.modifyModelByLength(id, length);
        logger.info("[end modifyModelByLength]");
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    //****************************** DELETE ********************************
    @Operation(summary = "Delete model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a model", content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting model", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/allvehicles/models/{id}")
    public ResponseEntity<Response> deleteModel(@PathVariable long id){
        logger.info("[init deleteModel]");
        modelService.deleteModel(id);
        logger.info("[end deleteModel]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
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
