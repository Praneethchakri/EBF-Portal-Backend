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

import com.ebf.EBF_portal.controller.CompanyController;
import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.Employee;
import com.ebf.EBF_portal.services.CompanyService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CompanyController.class)

public class CompanyControllerTest {
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CompanyControllerTest.class);
		@Autowired
		private MockMvc mockMvc;
		

		@MockBean
		private CompanyService companyService;

		Employee employee0 = new Employee(7l,"Praneeth C","Diguvapalem","praneeth.itdev@gmail.com","Frankfurt",200000,new Company(7l,"ABC"));
		

		@Test
		public void retrieveDetailsOfCompanybyID() throws Exception {
			Company compay = new Company();
				compay.setCompany_id(7l);
				compay.setCompany_name("ABC");
			employee0.setCompany(compay);
			System.out.println("Employee Object "+employee0.toString());
			System.out.println("Company "+compay.toString());
			System.out.println("Employee After adding Object "+employee0.toString());
			Mockito.when(companyService.getCompanyById(Mockito.anyLong())).thenReturn(compay);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v2/company/{id}",7).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			System.out.println("RESULT ~~~ > "+result.getResponse());
			String expected = "{\r\n"
					+ "  \"company_id\": 7,\r\n"
					+ "  \"company_name\": \"ABC\"\r\n"
					+ "}";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}
		
		@Test
		public void retrieveDetailsOfAllCompanies() throws Exception {
//			Employee employee0 = new Employee(7l,"Praneeth C","Diguvapalem","praneeth.itdev@gmail.com","Frankfurt",200000,new Company(7l,"ABC"));
//			Employee employee1 = new Employee(8l,"Sushma","Prasad","sushma.praneeth9391@gmail.com","Eschborn",400000,new Company(8l,"AWS"));
//			Employee employee2 = new Employee(13l,"PRajnaSri","Diguvapalem","Prajnasri@gmail.com","Frankfurt, Germany",500000,new Company(13l,"Alphabet.co"));
			Company compayfinal0 = new Company();
			
				compayfinal0.setCompany_id(7l);
				compayfinal0.setCompany_name("ABC");
			Company compayfinal1 = new Company();	
				compayfinal1.setCompany_id(8l);	
				compayfinal1.setCompany_name("AWS");
				
			List<Company> listOFCompanies = new ArrayList<Company>();
				listOFCompanies.add(compayfinal0);
				listOFCompanies.add(compayfinal1);
			System.out.println("List of companies : "+listOFCompanies);
			Mockito.when(companyService.getAllCompanies()).thenReturn(listOFCompanies);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v2/companies").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//			JSONObject json = new JSONObject(result);
//			System.out.println("Result "+json.toString());
			System.out.println("RESULT ~~~ > "+result.getResponse());
			String expected = "[\r\n"
					+ "  {\r\n"
					+ "    \"company_id\": 7,\r\n"
					+ "    \"company_name\": \"ABC\"\r\n"
					+ "  },\r\n"
					+ "  {\r\n"
					+ "    \"company_id\": 8,\r\n"
					+ "    \"company_name\": \"AWS\"\r\n"
					+ "  }\r\n"
					+ "]";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}
		
		@Test
		public void deleteCompanyByID() throws Exception {
			doThrow(new PersistenceException("Exception occured")).when(companyService)
					.deleteCompanyById(Mockito.anyLong());
//			Mockito.when(employeeService.deleteEmployeeById(Mockito.anyLong())).thenReturn(employee0);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
					"/api/v2/deleteCompany/{id}",7l).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println("RESULT ~~~ > "+result.getResponse());
			
//			String expected = "{\"Employee Details Deleted Succussfull.. \":true}";
			String expected = "{\"Issue To Delete Company\":false}";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}

	}
