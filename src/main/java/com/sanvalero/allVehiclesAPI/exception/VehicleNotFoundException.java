package com.sanvalero.allVehiclesAPI.exception;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }

    public VehicleNotFoundException(long id){
        super("Vehicle no found: " + id);
    }
}
