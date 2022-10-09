package com.ebf.EBF_portal.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ebf.EBF_portal.customExceptions.CompanyNotFound;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	private CompanyRepository companyRepository;
	 
		public CompanyServiceImpl(CompanyRepository companyRepository) {
			super();
			this.companyRepository = companyRepository;
		}

		
		@Override
		public List<Company> getAllCompanies() {
			// TODO Auto-generated method stub
			return companyRepository.findAll();
		}

		@Override
		public Company saveCompany(Company company) {
			// TODO Auto-generated method stub
			LOGGER.debug("Service Layer While Saving Employee details "+company.toString());
			return this.companyRepository.save(company);
		}

		@Override
		public Company getCompanyById(Long id) {
			// TODO Auto-generated method stub
			Optional<Company> company =  companyRepository.findById(id);
			if(company.isPresent()) {
				return company.get();
			}else {
				throw new CompanyNotFound("Company","CompanyCode",id);
			}
		}

		@Override
		public void deleteCompanyById(Long id) {
			this.companyRepository.deleteById(id);
			
		} 

		 


	
}
