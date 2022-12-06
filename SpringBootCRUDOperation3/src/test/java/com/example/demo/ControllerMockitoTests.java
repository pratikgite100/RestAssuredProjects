package com.example.demo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.beans.Country;
import com.example.demo.controllers.CountryController;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
    public List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getallCountries() {
	
		List<Country> myCountries1 = new ArrayList<Country>();
		myCountries1.add(new Country(1, "India", "Delhi"));
		myCountries1.add(new Country(2, "USA", "Washington"));
		myCountries1.add(new Country(3, "pp", "rr"));
		
		
		when(countryService.getAllCountries()).thenReturn(myCountries1);
	
		ResponseEntity<List<Country>> res = (ResponseEntity<List<Country>>) countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		System.out.println(res.getBody());
		assertEquals(3, res.getBody().size());
		
	}
	
	
	@Test
	@Order(2)
	public void test_getCountryById() {
	
		country = new Country(2, "USA", "Washington");
		int countryId =2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res =countryController.getCountryById(countryId);
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
	
		country = new Country(2, "USA", "Washington");
		String countryName ="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res =countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(countryName,res.getBody().getCountryName());
	}
	
	
	@Test
	@Order(4)
	public void test_addCountry() {
		
		Country country =  new Country(4,"Germany","Berlin");
		
		when(countryService.addCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> res = countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		//assertEquals(country, res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		
		Country country =  new Country(4,"Germany1","Berlin1");
	
		int countryId =4;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		
		when(countryService.updateCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> res = countryController.updateCountry(countryId, country);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		
		assertEquals(4,res.getBody().getId());
		assertEquals("Germany1",res.getBody().getCountryName());
		assertEquals("Berlin1",res.getBody().getCountryCapital());
		
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		
		country =  new Country(4,"Germany1","Berlin1");
		int countryId = 4;
		

		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countryController.deleteCountry(countryId);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		
		
		
	}

}
