package com.sanvalero.allVehiclesAPI.exception;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException() {
        super();
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }

    public CompanyNotFoundException(long id){
        super("Company not found: " + id);
    }
}
