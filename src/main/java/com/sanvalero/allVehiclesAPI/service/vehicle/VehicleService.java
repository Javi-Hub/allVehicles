package com.sanvalero.allVehiclesAPI.service.vehicle;

import com.sanvalero.allVehiclesAPI.domain.Vehicle;
import com.sanvalero.allVehiclesAPI.domain.dto.VehicleDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public interface VehicleService {

    Optional<Vehicle> findVehicleById(long id);
    Set<Vehicle> findVehicles();
    Vehicle addVehicle(long id, VehicleDTO vehicleDTO);
    Vehicle modifyVehicle(long id, VehicleDTO vehicleDTO);
    Vehicle modifyVehicleByPrice(long id, float price);
    void deleteVehicle(long id);
}
