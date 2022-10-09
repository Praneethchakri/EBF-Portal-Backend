package com.ebf.EBF_portal;

import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ebf.EBF_portal.controller.EmployeeController;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.Employee;
import com.ebf.EBF_portal.services.EmployeeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class)

public class EmployeeControllerTest {
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeControllerTest.class);
	
		@Autowired
		private MockMvc mockMvc;
		

		@MockBean
		private EmployeeService employeeService;
//			  "emp_id": 7,
//			  "name": "Praneeth C",
//			  "surname": "Diguvapalem",
//			  "email": "praneeth.itdev@gmail.com",
//			  "address": "Frankfurt",
//			  "salary": 200000,
//			  "company": {
//			    "company_id": 7,
//			    "company_name": "ABC"
//			  }
//			}
				Employee employee0 = new Employee(7l,"Praneeth C","Diguvapalem","praneeth.itdev@gmail.com","Frankfurt",200000,new Company(7l,"ABC"));
		

		@Test
		public void retrieveDetailsOfEmployeebyID() throws Exception {
			Company compay = new Company();
				compay.setCompany_id(7l);
				compay.setCompany_name("ABC");
			employee0.setCompany(compay);
			System.out.println("Employee Object "+employee0.toString());
			System.out.println("Company "+compay.toString());
			System.out.println("Employee After adding Object "+employee0.toString());
			Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(employee0);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v1/employees/{id}",7).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			System.out.println("RESULT ~~~ > "+result.getResponse());
			String expected = "{\r\n"
					+ "  \"emp_id\": 7,\r\n"
					+ "  \"name\": \"Praneeth C\",\r\n"
					+ "  \"surname\": \"Diguvapalem\",\r\n"
					+ "  \"email\": \"praneeth.itdev@gmail.com\",\r\n"
					+ "  \"address\": \"Frankfurt\",\r\n"
					+ "  \"salary\": 200000,\r\n"
					+ "  \"company\": {\r\n"
					+ "    \"company_id\": 7,\r\n"
					+ "    \"company_name\": \"ABC\"\r\n"
					+ "  }\r\n"
					+ "}";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}
		
		@Test
		public void retrieveDetailsOfAllEmployee() throws Exception {
			Employee employee0 = new Employee(7l,"Praneeth C","Diguvapalem","praneeth.itdev@gmail.com","Frankfurt",200000,new Company(7l,"ABC"));
			Employee employee1 = new Employee(8l,"Sushma","Prasad","sushma.praneeth9391@gmail.com","Eschborn",400000,new Company(8l,"AWS"));
			Employee employee2 = new Employee(13l,"PRajnaSri","Diguvapalem","Prajnasri@gmail.com","Frankfurt, Germany",500000,new Company(13l,"Alphabet.co"));
			Company compayfinal0 = new Company(7l,"ABC",employee0);
			Company compayfinal1 = new Company(8l,"AWS",employee1);
			Company compayfinal2 = new Company(13l,"Alphabet.co",employee2);
			employee0.setCompany(compayfinal0);
			employee1.setCompany(compayfinal1);
			employee2.setCompany(compayfinal2);
			
			List<Employee> listOFEmployees = new ArrayList<Employee>();
				listOFEmployees.add(employee0);
				listOFEmployees.add(employee1);
				listOFEmployees.add(employee2);
	
			
			Mockito.when(employeeService.getAllEmployees()).thenReturn(listOFEmployees);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v1/employees").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//			JSONObject json = new JSONObject(result);
//			System.out.println("Result "+json.toString());
			System.out.println("RESULT ~~~ > "+result.getResponse());
			String expected = "[\r\n"
					+ "  {\r\n"
					+ "    \"emp_id\": 7,\r\n"
					+ "    \"name\": \"Praneeth C\",\r\n"
					+ "    \"surname\": \"Diguvapalem\",\r\n"
					+ "    \"email\": \"praneeth.itdev@gmail.com\",\r\n"
					+ "    \"address\": \"Frankfurt\",\r\n"
					+ "    \"salary\": 200000,\r\n"
					+ "    \"company\": {\r\n"
					+ "      \"company_id\": 7,\r\n"
					+ "      \"company_name\": \"ABC\"\r\n"
					+ "    }\r\n"
					+ "  },\r\n"
					+ "  {\r\n"
					+ "    \"emp_id\": 8,\r\n"
					+ "    \"name\": \"Sushma\",\r\n"
					+ "    \"surname\": \"Prasad\",\r\n"
					+ "    \"email\": \"sushma.praneeth9391@gmail.com\",\r\n"
					+ "    \"address\": \"Eschborn\",\r\n"
					+ "    \"salary\": 400000,\r\n"
					+ "    \"company\": {\r\n"
					+ "      \"company_id\": 8,\r\n"
					+ "      \"company_name\": \"AWS\"\r\n"
					+ "    }\r\n"
					+ "  },\r\n"
					+ "  {\r\n"
					+ "    \"emp_id\": 13,\r\n"
					+ "    \"name\": \"PRajnaSri\",\r\n"
					+ "    \"surname\": \"Diguvapalem\",\r\n"
					+ "    \"email\": \"Prajnasri@gmail.com\",\r\n"
					+ "    \"address\": \"Frankfurt, Germany\",\r\n"
					+ "    \"salary\": 500000,\r\n"
					+ "    \"company\": {\r\n"
					+ "      \"company_id\": 13,\r\n"
					+ "      \"company_name\": \"Alphabet.co\"\r\n"
					+ "    }\r\n"
					+ "  }\r\n"
					+ "]";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}
		
		@Test
		public void deleteEmployeeByID() throws Exception {
			doThrow(new PersistenceException("Exception occured")).when(employeeService).deleteEmployeeById(Mockito.anyLong());
//			Mockito.when(employeeService.deleteEmployeeById(Mockito.anyLong())).thenReturn(employee0);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
					"/api/v1/deleteEmployee/{id}",7l).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//			JSONObject json = new JSONObject(result);
//			System.out.println("Result "+json.toString());
			LOGGER.info("RESULT ~~~ > "+result.getResponse());
			
//			String expected = "{\"Employee Details Deleted Succussfull.. \":true}";
			String expected = "{\"Issue with Deletion of employee Details/ Employee Not Found \":false}";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}

	}
