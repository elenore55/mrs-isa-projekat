package com.example.demo.controller;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.FilterShipDTO;
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
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipOwnerControllerTest {

    private static final String URL_PREFIX = "/api/shipOwner";

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
    @WithMockUser(roles = {"SHIP"})
    public void testGetShips() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getShips/1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Sirena"))
                .andExpect(jsonPath("$[0].price").value(150));
    }

    @Test
    @WithMockUser(roles = {"SHIP"})
    public void testGetShipsSearch() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getShips/1/brod")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Sirena"))
                .andExpect(jsonPath("$[0].price").value(150));
    }

    @Test
    @WithMockUser(roles = {"SHIP"})
    public void testFilterShips() throws Exception {
        FilterShipDTO dto = new FilterShipDTO();
        dto.setSortParam("city");
        dto.setSortDir("ascending");
        dto.setHighPrice(300);
        dto.setLowPrice(50);
        String json = TestUtil.json(dto);
        mockMvc.perform(post(URL_PREFIX + "/filterShips/1").contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Sirena"))
                .andExpect(jsonPath("$[0].price").value(150));
    }
}
