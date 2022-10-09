package com.ebf.EBF_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebf.EBF_portal.model.Company;
import com.ebf.EBF_portal.model.Employee;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
