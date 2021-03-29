package com.sanvalero.allVehiclesAPI.service.model;

import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public interface ModelService {

    Optional<Model> findModelById(long id);
    Set<Model> findModels();
    Model addModel(long id, ModelDTO modelDTO);
    Model modifyModel(long id, ModelDTO modelDTO);
    Model modifyModelUnits(long id, int units);
    void deleteModel(long id);

}
