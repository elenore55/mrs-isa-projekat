package com.example.demo.controller;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.ProfileDataDTO;
import com.example.demo.dto.UserFilterDTO;
import com.example.demo.model.Address;
import com.example.demo.model.ProfileData;
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

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

    private static final String URL_PREFIX = "/api/admin";

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
    public void testAddAdmin() throws Exception {
        AdminDTO a = new AdminDTO();
        a.setIs_main(false);
        ProfileDataDTO pd = new ProfileDataDTO();
        pd.setName("Marko");
        pd.setSurname("Markovic");
        pd.setEmail("marko@markovic.com");
        pd.setPassword("Neki novi pass");
        pd.setPhoneNumber("065998105");
        pd.setAddress(new Address("Teslic", "Bosna", "Ulica ili sta vec"));
        a.setProfileDataDTO(pd);

        String json = TestUtil.json(a);
        this.mockMvc.perform(post(URL_PREFIX + "/addAdmin").contentType(contentType).content(json))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.is_main").value(false))
                .andExpect(jsonPath("$.profileDataDTO.name").value("Marko"));
    }

    @Test
    public void testUpdateAdmin() throws Exception {

        AdminDTO a = new AdminDTO();
        a.setIs_main(false);
        a.setId(4);

        ProfileDataDTO pd = new ProfileDataDTO();
        pd.setName("Marko");
        pd.setSurname("Markovic");
        pd.setEmail("marko@markovic.com");
        pd.setPassword("Neki novi pass");
        pd.setPhoneNumber("065998105");
        pd.setAddress(new Address("Teslic", "Bosna", "Ulica ili sta vec"));
        a.setProfileDataDTO(pd);

        String json = TestUtil.json(a);
        this.mockMvc.perform(post(URL_PREFIX + "/updateAdminInfo").contentType(contentType).content(json))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.is_main").value(false))
                .andExpect(jsonPath("$.profileDataDTO.name").value("Marko"));
    }


}
