package com.ebf.EBF_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ebf.EBF_portal.model")
public class EbfPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbfPortalApplication.class, args);
	}

}
