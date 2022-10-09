package com.ebf.EBF_portal.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ebf.EBF_portal.controller.EmployeeController;
import com.ebf.EBF_portal.customExceptions.EmployeeNotFound;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.Employee;
import com.ebf.EBF_portal.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private EmployeeRepository employeeRepository;
	 
		public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
			super();
			this.employeeRepository = employeeRepository;
		} 

//		public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//			this.employeeRepository = employeeRepository;
//		}


//		public EmployeeServiceImpl() { 
//			// TODO Auto-generated constructor }
//		}
		 

	@Override
	public List<Employee> getAllEmployees() {
		LOGGER.info("In Service Layer GetAllthe Employees ..");
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		LOGGER.info("In Service Layer Adding New Employee "+employee.toString());
		
//		   Company company = employee.getCompany();
//		   company.setEmployee(employee);
		return this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		LOGGER.info("In Service Layer Getting  Employee details of  "+id);
		Optional<Employee> employee =  employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new EmployeeNotFound("Employee","Emp_ID",id);
		}
	}

	@Override
	public void deleteEmployeeById(Long id) {
		LOGGER.info("Service layer while Deleting of Employee .."+id);
		this.employeeRepository.deleteById(id);
	}

	
	
}
