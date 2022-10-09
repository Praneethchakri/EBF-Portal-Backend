package com.ebf.EBF_portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebf.EBF_portal.customExceptions.CompanyNotFound;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.services.CompanyService;
import com.ebf.EBF_portal.services.EmployeeServiceImpl;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/api/v2" })
public class CompanyController {
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	// get all employees
	@GetMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}

	// create employee rest api
	@PostMapping("/createCompany")
	public Company createCompany(@RequestBody Company company) {
		LOGGER.info("Company Controller : " + company.toString());
		return companyService.saveCompany(company);
	}
	
	// get employee by id rest api
		@GetMapping("/company/{id}")
		public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
			Company company=null;
			try {
				company = companyService.getCompanyById(id);
			}catch(CompanyNotFound e) {
				LOGGER.error("Company Detials Not Found to Display of ",id);
				throw new CompanyNotFound("Company", "Company_id", id);
			}
			return ResponseEntity.ok(company);
		}

	// update employee rest api

	@PutMapping("/updateCompany/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetials) {
		System.out.println("controller while Update~~~ "+companyDetials.toString());
		Company updatedCompany = null;
		try {
			Company company = companyService.getCompanyById(id);

			company.setCompany_id(companyDetials.getCompany_id());
			company.setCompany_name(companyDetials.getCompany_name());
			
			updatedCompany = companyService.saveCompany(updatedCompany);
		} 
		catch (CompanyNotFound compNotFound) {
			LOGGER.error("Company Detials Not Found to Update",id);
			throw new CompanyNotFound("Compay", "Company_id", id);
		}
		return ResponseEntity.ok(updatedCompany);
	}

	// delete employee rest api
	@DeleteMapping("/deleteCompany/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable Long id) {
		System.out.println("controller while Update~~~ "+id);
		Map<String, Boolean> response = new HashMap<>();
		try {
			Company company = companyService.getCompanyById(id);
			if (company != null) {
				companyService.deleteCompanyById(id);
				response.put("deleted", Boolean.TRUE);
			} else {
				response.put("Issue To Delete Company", Boolean.FALSE);
			}
		} catch (CompanyNotFound compNotDound) {
			LOGGER.error("Company Detials Not Found to Delete",id);
			throw new CompanyNotFound("Company", "Comp_id", id);
		} 
		return ResponseEntity.ok(response);
	}



}
