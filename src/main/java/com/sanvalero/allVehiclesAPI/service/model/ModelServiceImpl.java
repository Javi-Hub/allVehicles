package com.sanvalero.allVehiclesAPI.service.model;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.repository.BrandRepository;
import com.sanvalero.allVehiclesAPI.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Optional<Model> findModelById(long id) {
        return modelRepository.findById(id);
    }

    @Override
    public Set<Model> findModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model addModel(long id, ModelDTO modelDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        Model model = new Model();
        model.setName(modelDTO.getName());
        model.setType(modelDTO.getType());
        model.setUnits(modelDTO.getUnits());
        model.setAvailable(modelDTO.isAvailable());
        model.setMarketLaunch(modelDTO.getMarketLaunch());
        model.setBrand(brand);
        return modelRepository.save(model);
    }

    @Override
    public Model modifyModel(long id, ModelDTO modelDTO) {
        return null;
    }

    @Override
    public Model modifyModelUnits(long id, int units) {
        return null;
    }

    @Override
    public void deleteModel(long id) {

    }
}
