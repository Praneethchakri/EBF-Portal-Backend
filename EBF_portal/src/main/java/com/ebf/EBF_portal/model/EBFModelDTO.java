package com.ebf.EBF_portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EBFModelDTO {
	private Employee employee;
	private Company company;
	@Override
	public String toString() {
		return "EBFModelDTO [employee=" + employee + ", company=" + company + "]";
	}
	
	
	

}
