package com.example.demo;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.beans.Country;
import com.example.demo.controllers.CountryController;
import com.example.demo.services.CountryService;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.restservices.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMvcTests.class})
public class ControllerMockMvcTests {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> myCountries;
	Country country;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
		
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington"));
		myCountries.add(new Country(3, "pp", "rr"));
		
		when(countryService.getAllCountries()).thenReturn(myCountries);
		
		this.mockMvc.perform(get("/getcountries"))
		   .andExpect(status().isFound())
		   .andDo(print());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
		
		country = new Country(2,"USA","Washington");
		int countryId=2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		
		this.mockMvc.perform(get("/getCountries/{id}",countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washington"))
		.andDo(print());
		
		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		
		country = new Country(2,"USA","Washington");
		String countryName="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		
		this.mockMvc.perform(get("/getCountries/countryname").param("name","USA"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washington"))
		.andDo(print());
		
		
	}
	
	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
		
		country = new Country(4,"Germany", "Berlin");
		
		
		
	}

}
