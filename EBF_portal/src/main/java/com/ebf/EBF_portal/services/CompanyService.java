package com.ebf.EBF_portal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.Employee;
//@Component
public interface CompanyService {
	List<Company> getAllCompanies();
	Company saveCompany(Company company);
	Company getCompanyById(Long id);
	void deleteCompanyById(Long id);
}
