package com.sanvalero.allVehiclesAPI.exception;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(long id){
        super("Model not found: " + id);
    }
}
