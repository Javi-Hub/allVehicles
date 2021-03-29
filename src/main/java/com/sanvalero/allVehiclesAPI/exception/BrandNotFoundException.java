package com.sanvalero.allVehiclesAPI.exception;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException() {
    }

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException(long id){
        super("Brand not found: " + id);
    }
}
