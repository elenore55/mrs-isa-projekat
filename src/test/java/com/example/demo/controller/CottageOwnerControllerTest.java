package com.example.demo.controller;

import com.example.demo.dto.FilterCottageDTO;
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
public class CottageOwnerControllerTest {

    private static final String URL_PREFIX = "/api/cottageOwner";

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
    @WithMockUser(roles = {"COTTAGE"})
    public void testGetCottages() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getCottages/2")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].name").value("Frida River House"))
                .andExpect(jsonPath("$[0].price").value(300));
    }

    @Test
    @WithMockUser(roles = {"COTTAGE"})
    public void testGetCottagesSearch() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getCottages/2/house")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Frida River House"))
                .andExpect(jsonPath("$[0].price").value(300));
    }

    @Test
    @WithMockUser(roles = {"COTTAGE"})
    public void testFilterCottages() throws Exception {
        FilterCottageDTO dto = new FilterCottageDTO();
        dto.setSortParam("city");
        dto.setSortDir("ascending");
        dto.setHigh(300);
        dto.setLow(150);
        String json = TestUtil.json(dto);
        mockMvc.perform(post(URL_PREFIX + "/filterCottages/2").contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Frida River House"))
                .andExpect(jsonPath("$[0].price").value(300));
    }
}
