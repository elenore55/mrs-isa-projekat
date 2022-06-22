package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CottageDTO;
import com.example.demo.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressControllerTest {

    private static final String URL_PREFIX = "/api/addresses";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetCities() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getCities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0]").value("London"));
        mockMvc.perform(get(URL_PREFIX + "/getCities"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[5]").value("Venecija"));
    }

    @Test
    public void testGetCountries() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getCountries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]").value("Srbija"));
        mockMvc.perform(get(URL_PREFIX + "/getCountries"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[1]").value("Francuska"));
    }


}
