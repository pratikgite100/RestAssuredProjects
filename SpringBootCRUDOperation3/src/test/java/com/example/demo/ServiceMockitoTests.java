package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.beans.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;



@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	@Mock
	CountryRepository countryRep;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> myCountries;
	
	
	@Test
	@Order(1)
	public void test_getallCountries() {
	
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington"));
		myCountries.add(new Country(3, "pp", "rr"));
		
		when(countryRep.findAll()).thenReturn(myCountries);   ///Mocking
		assertEquals(3,countryService.getAllCountries().size());
	}

	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington"));
		myCountries.add(new Country(3, "pp", "rr"));
		
		
		int countryId=1;
		
		when(countryRep.findAll()).thenReturn(myCountries);   ///Mocking
		
		assertEquals(countryId, countryService.getCountryById(countryId).getId());
		
	}
	
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington"));
		myCountries.add(new Country(3, "pp", "rr"));
		
		
		String countryName="India";
		
		when(countryRep.findAll()).thenReturn(myCountries);   ///Mocking
		
		assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
		
	}
	
	
	@Test
	@Order(4)
	public void test_addCountry() {
		
		Country country =  new Country(4,"Germany","Berlin");
		
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country, countryService.addCountry(country));
		
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		
		Country country =  new Country(4,"Germany1","Berlin1");
		
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country, countryService.updateCountry(country));
		
	}
	
	
	@Test
	@Order(5)
	public void test_deleteCountry() {
		
		Country country =  new Country(4,"Germany1","Berlin1");
		

		countryService.deleteCountry(country);
		
		verify(countryRep,times(1)).delete(country);
		
	}
	
}
