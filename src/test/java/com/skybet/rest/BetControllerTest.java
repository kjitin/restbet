package com.skybet.rest;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith (SpringRunner.class)
@WebMvcTest(BetController.class)
public class BetControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	

	@Test
	public void getBetsFromService() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/available")).andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].bet_id",is(1)));
		 
	}
	
	@Test
	public void postBetsToService() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/bets").contentType(MediaType.APPLICATION_JSON)
				 .content("{\"bet_id\":1,\"odds\":11.0,\"stake\":10}\")"))
				 .andExpect(MockMvcResultMatchers.status().isCreated())
				 .andExpect(MockMvcResultMatchers.jsonPath("$.bet_id",is(1)));
		 
	}
	
	@Test
	public void postBetsToServiceWithWrongBetId() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/bets").contentType(MediaType.APPLICATION_JSON)
				 .content("{\"bet_id\":10002,\"odds\":11.0,\"stake\":10}\")"))
				 .andExpect(MockMvcResultMatchers.status().isIAmATeapot());
		 
	}
	
	@Test
	public void postBetsToServiceWithBetId0() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/bets").contentType(MediaType.APPLICATION_JSON)
				 .content("{\"bet_id\":0,\"odds\":11.0,\"stake\":10}\")"))
				 .andExpect(MockMvcResultMatchers.status().isBadRequest());
		 
	}
	
	@Test
	public void postBetsToServiceWithIncorrectJSON() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/bets").contentType(MediaType.APPLICATION_JSON)
				 .content("{\"odds\":11.0,\"stake\":10}\")"))
				 .andExpect(MockMvcResultMatchers.status().isBadRequest());
		 
	}
	
	@Test
	public void postBetsToServiceWithInValidJSON() throws Exception{
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/bets").contentType(MediaType.APPLICATION_JSON)
				 .content("{\"odds\"11.0,\"stake\":10}\")"))
				 .andExpect(MockMvcResultMatchers.status().isBadRequest());
		 
	}

	
	@Test
	public void getDecimalValueWithCorrectValues(){
		BetController betController = new BetController();
		Assert.assertEquals(3.75, betController.getDecimalValue(11, 4),0.001);
		Assert.assertEquals(1.22, betController.getDecimalValue(2, 9),0.001);
		
	}
	
	
}
