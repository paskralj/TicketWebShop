package com.web.training;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class WebAppTrainingApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}
	
	@Test
	public void testShowHomePage() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?=1");
		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(model().attributeExists("ticket"))
		.andExpect(view().name("form"));	
	}
	
	@Test
	public void testShowTablePage() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/table");
		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(model().attributeExists("ticketinfo"))
		.andExpect(view().name("table"));
	}
	
	@Test
	public void testSuccessfulItemSubmit() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/submitItem")
				.param("city", "Berlin")
				.param("category", "Category Two (30e)")
				.param("ticketNumb", "3")
				.param("firstname", "Euro")
				.param("lastname", "InGermany")
				.param("id", "1950");
		
		mockMvc.perform(request)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/table"));
	}
	
	@Test
	public void testUnsuccessfulItemSubmit() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/submitItem")
				.param("city", "")
				.param("category", "")
				.param("ticketNumb", "-2")
				.param("firstname", "duplicated")
				.param("lastname", "duplicated")
				.param("id", "0");
		
		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("form"));
	}
	

}
