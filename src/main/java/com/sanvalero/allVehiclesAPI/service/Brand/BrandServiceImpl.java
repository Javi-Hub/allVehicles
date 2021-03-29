package com.sanvalero.allVehiclesAPI.service.Brand;

import com.sanvalero.allVehiclesAPI.domain.Brand;
import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.domain.dto.BrandDTO;
import com.sanvalero.allVehiclesAPI.exception.BrandNotFoundException;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
import com.sanvalero.allVehiclesAPI.repository.BrandRepository;
import com.sanvalero.allVehiclesAPI.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Brand> findBrandById(long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Set<Brand> findBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand addBrand(long id, BrandDTO brandDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setRanking(brandDTO.getRanking());
        brand.setOperational(brandDTO.isOperational());
        brand.setAssessment(brandDTO.getAssessment());
        brand.setFoundationDate(brandDTO.getFoundationDate());
        brand.setCompany(company);
        return brandRepository.save(brand);
    }

    @Override
    public Brand modifyBrand(long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        brand.setName(brandDTO.getName());
        brand.setRanking(brandDTO.getRanking());
        brand.setOperational(brandDTO.isOperational());
        brand.setAssessment(brandDTO.getAssessment());
        brand.setFoundationDate(brandDTO.getFoundationDate());
        return brandRepository.save(brand);
    }

    @Override
    public Brand modifyBrandAssessment(long id, float assessment) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        brand.setAssessment(assessment);
        return brandRepository.save(brand);

    }

    @Override
    public Brand modifyBrandByAssessment(long id, float assessment) {
        return null;
    }

    @Override
    public void deleteBrand(long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        brandRepository.deleteById(id);
    }
}
