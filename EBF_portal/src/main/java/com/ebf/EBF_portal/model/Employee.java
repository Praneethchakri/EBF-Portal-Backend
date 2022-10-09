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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emp_id;
	@Column(nullable =false)
	private String name;
	@Column(nullable =false)
	private String surname;
	@Column(nullable =false)
	private String email;
	@Column(nullable =false)
	private String address;
	@Column(nullable =false)	
	private double salary;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employee")
	@PrimaryKeyJoinColumn
    @JsonManagedReference
    private Company company;

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", address=" + address + ", salary=" + salary + ", company=" + company + "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(address, company, email, emp_id, name, salary, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(address, other.address) && Objects.equals(company, other.company)
				&& Objects.equals(email, other.email) && Objects.equals(emp_id, other.emp_id)
				&& Objects.equals(name, other.name) && Objects.equals(salary, other.salary)
				&& Objects.equals(surname, other.surname);
	}
	
 
}
