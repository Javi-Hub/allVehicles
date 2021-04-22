package com.sanvalero.allVehiclesAPI.service.vehicle;

import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.Vehicle;
import com.sanvalero.allVehiclesAPI.domain.dto.VehicleDTO;
import com.sanvalero.allVehiclesAPI.exception.ModelNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.VehicleNotFoundException;
import com.sanvalero.allVehiclesAPI.repository.ModelRepository;
import com.sanvalero.allVehiclesAPI.repository.VehicleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRespository vehicleRespository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Optional<Vehicle> findVehicleById(long id) {
        return vehicleRespository.findById(id);
    }

    @Override
    public Set<Vehicle> findVehicles() {
        return vehicleRespository.findAll();
    }

    @Override
    public Vehicle addVehicle(long id, VehicleDTO vehicleDTO) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        Vehicle vehicle = new Vehicle();
        setVehicle(vehicle, vehicleDTO);
        vehicle.setModel(model);
        return vehicleRespository.save(vehicle);
    }

    @Override
    public Vehicle modifyVehicle(long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = findVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        setVehicle(vehicle, vehicleDTO);
        return vehicleRespository.save(vehicle);
    }

    @Override
    public Vehicle modifyVehicleByPrice(long id, float price) {
        Vehicle vehicle = vehicleRespository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        vehicle.setPrice(price);
        return vehicleRespository.save(vehicle);
    }

    @Override
    public void deleteVehicle(long id) {
        Vehicle vehicle = vehicleRespository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        vehicleRespository.delete(vehicle);
    }

    public void setVehicle(Vehicle vehicle, VehicleDTO vehicleDTO){
        vehicle.setName(vehicleDTO.getName());
        vehicle.setHorsePower(vehicleDTO.getHorsePower());
        vehicle.setEcoLabel(vehicleDTO.isEcoLabel());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setConsumption(vehicleDTO.getConsumption());
        vehicle.setMarketLaunch(vehicleDTO.getMarketLaunch());
    }
}