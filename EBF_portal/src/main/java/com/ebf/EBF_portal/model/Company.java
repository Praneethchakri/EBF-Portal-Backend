package com.ebf.EBF_portal.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(nullable =false)
	private Long company_id;
	
	@Column(name="company_name" ,nullable =false)
	private String company_name;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    @JsonBackReference
    private Employee employee;

	@Override
	public String toString() {
		return "Company [company_id=" + company_id + ", company_name=" + company_name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(company_id, company_name, employee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(company_id, other.company_id) && Objects.equals(company_name, other.company_name)
				&& Objects.equals(employee, other.employee);
	}

	public Company(long l, String string) {
		// TODO Auto-generated constructor stub
	}
	
	


}
