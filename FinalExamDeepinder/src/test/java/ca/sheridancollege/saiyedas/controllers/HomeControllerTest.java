package ca.sheridancollege.saiyedas.controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.saiyedas.beans.Capstone;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoadingIndex() throws Exception {
		this.mockMvc.perform(get("/").flashAttr("capstone", new Capstone())).andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void testLoadingInsertCapstone() throws Exception {
		Capstone capstone = new Capstone();
		capstone.setName("david");
		capstone.setVote(8);
		capstone.setInformation("youtube");
		
		this.mockMvc.perform(post("/insertCapstone").flashAttr("capstone", capstone)).andExpect(status().isOk())
				
		.andExpect(view().name("index"));
	}

}