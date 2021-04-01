package com.sanvalero.allVehiclesAPI.repository;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {

    Set<Brand> findAll();
    Brand findByName(String name);

}
