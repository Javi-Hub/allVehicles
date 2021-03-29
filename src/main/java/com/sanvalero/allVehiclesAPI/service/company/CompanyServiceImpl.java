package com.sanvalero.allVehiclesAPI.service.company;

import com.sanvalero.allVehiclesAPI.domain.Company;
import com.sanvalero.allVehiclesAPI.exception.CompanyNotFoundException;
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
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> findCompanyById(long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Set<Company> findCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company modifyCompany(long id, Company newCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        newCompany.setId(company.getId());
        return companyRepository.save(newCompany);
    }

    public Company modifyCompanyBySharesStock(long id, float sharesStock) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        company.setSharesStock(sharesStock);
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        companyRepository.deleteById(id);
    }
}
