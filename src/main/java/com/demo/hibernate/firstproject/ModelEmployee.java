package com.demo.hibernate.firstproject;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@NamedQueries({@NamedQuery(name = "employee.getListofEmployee" , query = "select employee from ModelEmployee employee")})

@Entity
@Table(name="employee")
public class ModelEmployee {
	
	public static final String GET_EMPLOYEE_LIST = "employee.getListofEmployee";
	
	@Id
	private String id;
	@Column(name = "emp_name", columnDefinition = "varchar(25)")
	private String empName;
	
	@Formula("count(*)")
	private int count;
	
	@Column(name = "emp_salary", precision = 7 , scale = 2)
	private BigDecimal empSalary;

	

	public BigDecimal getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(BigDecimal empSalary) {
		this.empSalary = empSalary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
	
	
}
