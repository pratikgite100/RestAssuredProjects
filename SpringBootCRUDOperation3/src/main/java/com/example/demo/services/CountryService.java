package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Country;
import com.example.demo.controllers.AddResponse;
import com.example.demo.repositories.CountryRepository;



@Component
@Service
public class CountryService {
	
	
	@Autowired
	CountryRepository countryRep;
	
	
	
	public List<Country> getAllCountries() {
		List<Country> countries = countryRep.findAll();
		return countries;
		
	 }
	
	public Country getCountryById(int id) {
		
		List<Country> countries = countryRep.findAll();
		Country country =null;
    
		for(Country con:countries) {
			if(con.getId()==id) {
				country=con;
			}
		    
		}
		return country;
	}		
	
	public Country getCountryByName(String countryName){
		
		List<Country> countries = countryRep.findAll();
		Country country =null;
		for(Country con:countries) {
			
			if(con.getCountryName().equals(countryName)) {
				country=con;
			}
			
		}
		return country;
		
	}
	
	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryRep.save(country);
		return country;
		
	}
	
	
	public Country updateCountry(Country country) {
		countryRep.save(country);
		return country;
	}
	
	
	//utility method to get max id
	public int getMaxId() {
		
		return countryRep.findAll().size()+1;
	}
	
	
	
	public AddResponse deleteCountry(Country country) {
		
		countryRep.delete(country);
		return null;
	}
	
	
	

}
