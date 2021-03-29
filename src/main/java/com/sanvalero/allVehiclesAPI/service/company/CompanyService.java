package com.sanvalero.allVehiclesAPI.service.company;

import com.sanvalero.allVehiclesAPI.domain.Company;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 29/03/2021
 */
public interface CompanyService {

    Optional<Company> findCompanyById(long id);
    Set<Company> findCompanies();
    Company addCompany(Company company);
    Company modifyCompany(long id, Company company);
    Company modifyCompanyBySharesStock(long id, float sharesStock);
    void deleteCompany(long id);

}
