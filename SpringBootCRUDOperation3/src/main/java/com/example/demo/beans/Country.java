package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country  {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "capital")
	private String countryCapital;
	
	
	public Country(int id, String countryName, String countryCapital) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.countryName=countryName;
		this.countryCapital=countryCapital;
	}
	
	public Country() {
		
	}
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

}
