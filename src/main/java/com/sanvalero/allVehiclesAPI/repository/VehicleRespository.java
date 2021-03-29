package com.sanvalero.allVehiclesAPI.repository;

import com.sanvalero.allVehiclesAPI.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Repository
public interface VehicleRespository extends CrudRepository<Vehicle, Long> {

    Set<Vehicle> findAll();

}
