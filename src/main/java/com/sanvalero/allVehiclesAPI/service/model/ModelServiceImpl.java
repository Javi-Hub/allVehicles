package com.sanvalero.allVehiclesAPI.service.model;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.domain.Model;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.domain.dto.ModelDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.ModelNotFoundException;
import com.sanvalero.allVehiclesAPI.repository.BrandRepository;
import com.sanvalero.allVehiclesAPI.repository.CompanyRepository;
import com.sanvalero.allVehiclesAPI.repository.ModelRepository;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Model> findModelById(long id) {
        return modelRepository.findById(id);
    }

    @Override
    public Set<Model> findModels() {
        return modelRepository.findAll();
    }

    @Override
    public Set<Model> findByNameAndTypeAndAvailable(String name, String type, boolean available) {
        return modelRepository.findByNameAndTypeAndAvailable(name, type, available);
    }

    @Override
    public Set<Model> findByCompanyAndBrandAndUnitsmodel(String brand, String type, int maxUnit, int minUnit) {
        Brand searchBrand = brandRepository.findByName(brand);

        List<Model> models = searchBrand.getModelList();
        Set<Model> searchModels = models.stream()
                .filter(model -> model.getType().equals(type))
                .filter(model -> model.getUnits() <= maxUnit)
                .filter(model -> model.getUnits() >= minUnit)
                .filter(model -> model.getBrand().equals(searchBrand))
                .collect(Collectors.toSet());

        return searchModels;
    }

    @Override
    public Model findByName(String name) {
        return modelRepository.findByName(name);
    }

    @Override
    public Model addModel(long id, ModelDTO modelDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        Model model = new Model();
        model.setBrand(brand);
        setModel(model, modelDTO);
        return modelRepository.save(model);
    }

    @Override
    public Model modifyModel(long id, ModelDTO modelDTO) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        setModel(model, modelDTO);
        return modelRepository.save(model);
    }

    @Override
    public Model modifyModelByUnits(long id, int units) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        model.setUnits(units);
        return modelRepository.save(model);
    }

    @Override
    public Model modifyModelByLength(long id, float length) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        model.setLength(length);
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        modelRepository.delete(model);
    }

    public void setModel(Model model, ModelDTO modelDTO){
        model.setName(modelDTO.getName());
        model.setType(modelDTO.getType());
        model.setUnits(modelDTO.getUnits());
        model.setAvailable(modelDTO.isAvailable());
        model.setLength(modelDTO.getLength());
        model.setMarketLaunch(modelDTO.getMarketLaunch());
    }
}
