package com.sanvalero.allVehiclesAPI.service.model;

import com.sanvalero.allVehiclesAPI.domain.Brand;
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
    Set<Model> findByNameAndTypeAndAvailable(String name, String type, boolean available);
    Model findByName(String name);
    Model addModel(long id, ModelDTO modelDTO);
    Model modifyModel(long id, ModelDTO modelDTO);
    Model modifyModelByUnits(long id, int units);
    Model modifyModelByLength(long id, float length);
    void deleteModel(long id);

}
