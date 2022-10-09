package com.ebf.EBF_portal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ebf.EBF_portal.model.Employee;
//@Component
public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee saveEmployee(Employee employee);
	Employee getEmployeeById(Long id);
	void deleteEmployeeById(Long id);
//	Long getAvgSalaryOfEmployees(List<Employee> emp)l
}
