package com.ebf.EBF_portal;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ebf.EBF_portal.services.CompanyService;
import com.ebf.EBF_portal.services.EmployeeService;

@SpringBootTest
class EbfPortalApplicationTests {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompanyService companyService;
	@Test
	@IgnoreForBinding
	void contextLoads() {
		assertThat(employeeService).isNotNull();
		assertThat(companyService).isNotNull();
	}

}
