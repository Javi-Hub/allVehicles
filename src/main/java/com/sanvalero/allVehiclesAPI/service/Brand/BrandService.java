package com.sanvalero.allVehiclesAPI.service.Brand;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public interface BrandService {

    Optional<Brand> findBrandById(long id);
    Set<Brand> findBrands();
    Brand addBrand(long id, BrandDTO brandDTO);
    Brand modifyBrand(long id, BrandDTO brandDTO);
    Brand modifyBrandAssessment(long id, float assessment);
    Brand modifyBrandByAssessment(long id, float assessment);
    void deleteBrand(long id);
}
