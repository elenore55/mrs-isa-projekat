package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CottageDTO;
import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageControllerTest {

    private static final String URL_PREFIX = "/api/cottages";

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
    public void testGetCottages() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getCottages")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].name").value("Frida River House"))
                .andExpect(jsonPath("$[0].price").value(300));
    }

    @Test
    @Transactional
    @WithMockUser(roles = {"COTTAGE"})
    public void testSaveCottage() throws Exception {
        CottageDTO cottage = new CottageDTO();
        cottage.setName("c1");
        cottage.setPrice(BigDecimal.valueOf(200));
        cottage.setDescription("Description");
        cottage.setAddress(new AddressDTO("street", "city", "country"));
        cottage.setOwnerId(2);
        String json = TestUtil.json(cottage);
        this.mockMvc.perform(post(URL_PREFIX + "/addCottage").contentType(contentType).content(json)).andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(roles = {"COTTAGE"})
    public void testDeleteCottage() throws Exception {
        mockMvc.perform(delete(URL_PREFIX + "/deleteCottage/5")).andExpect(status().isOk());
    }
}
