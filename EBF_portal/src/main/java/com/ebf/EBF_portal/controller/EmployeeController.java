package com.ebf.EBF_portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebf.EBF_portal.customExceptions.EmployeeNotFound;
import com.ebf.EBF_portal.customExceptions.EmployeeServiceException;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.EBFModelDTO;
import com.ebf.EBF_portal.model.Employee;
import com.ebf.EBF_portal.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/api/v1" })
public class EmployeeController {

	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		LOGGER.debug("Controller LAyer :: method:getAllEmployees");
		return employeeService.getAllEmployees();
	}

	// create employee rest api
	@PostMapping("/createEmployees")
	public Employee createEmployee(@RequestBody EBFModelDTO ebfModel) {
		LOGGER.debug("Controller Layer :: createEmployee");
		LOGGER.debug("EBF Model " + ebfModel.toString());
		Company companyDetails = ebfModel.getCompany();
		companyDetails.setEmployee(ebfModel.getEmployee());

		LOGGER.debug("company Details from UI : " + companyDetails.toString());
		Employee employee = ebfModel.getEmployee();
		employee.setCompany(companyDetails);
		LOGGER.debug("Employess from UI After Adding Company : " + employee.toString());
		return employeeService.saveEmployee(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		LOGGER.debug("Controller Layer :: method:getEmployeeById");
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeById(id);
		} catch (EmployeeNotFound e) {
			throw new EmployeeNotFound("Employee", "emp_id", id);
		}
		return ResponseEntity.ok(employee);
	}

	// update employee rest api

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EBFModelDTO ebfModel) {
		LOGGER.debug("controller while Update~~~ " + ebfModel.toString());
		Employee employeeFromUI = ebfModel.getEmployee();
		Employee updatedEmployee = null;
		ebfModel.setCompany(ebfModel.getEmployee().getCompany());
//		System.out.println("Processed Model Employee ~~~~"+employeeFromUI.toString());
//		System.out.println("Processed Modelafter Adding Company  ~~~~"+ebfModel.toString());
		try {
			Employee employee = employeeService.getEmployeeById(id);
			employee.setName(employeeFromUI.getName());
			employee.setSurname(employeeFromUI.getSurname());
			employee.setEmail(employeeFromUI.getEmail());
			employee.setAddress(employeeFromUI.getAddress());
			employee.setSalary(employeeFromUI.getSalary());
			employee.setCompany(ebfModel.getCompany());
			LOGGER.info("After Merging Company " + employee.toString());
			updatedEmployee = employeeService.saveEmployee(employee);
		} catch (EmployeeNotFound empNotFound) {
			LOGGER.error("Employee Not Found At Controller: ", id);
			throw new EmployeeNotFound("Employee", "Emp_id", id);
		}
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		System.out.println("controller while Update~~~ " + id);
		Map<String, Boolean> response = new HashMap<>();
		try {
			Employee employee = employeeService.getEmployeeById(id);
			if (employee != null) {
				employeeService.deleteEmployeeById(id);
				response.put("Employee Details Deleted Succussfull.. ", Boolean.TRUE);
			} else {
				response.put("Issue with Deletion of employee Details/ Employee Not Found ", Boolean.FALSE);
			}
		} catch (EmployeeNotFound empNotDound) {
			LOGGER.error("Employee Not Found At Controller to Delete: ", id);
			throw new EmployeeNotFound(null, null, id);
		} catch (EmployeeServiceException empDeleatd) {
			LOGGER.error("Employee is already Deleted.. At Controller: ", id);
			throw new EmployeeServiceException("Employee is already Deleted..");
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/avgSalary")
	public ResponseEntity<Double> employessAvgSalary() {
//		Double d = new Double(266666);
		LOGGER.info("Avg Salary Controller Called...");
		List<Employee> all_emp = employeeService.getAllEmployees();
		return ResponseEntity.ok(all_emp.stream().mapToDouble(Employee::getSalary).average().getAsDouble());
	}

}
