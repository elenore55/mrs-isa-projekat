package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.ShipDTO;
import com.example.demo.dto.UserFilterDTO;
import com.example.demo.model.enums.ShipType;
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
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipControllerTest {

    private static final String URL_PREFIX = "/api/ships";

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
    public void testGetShips() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getShips")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Sirena"))
                .andExpect(jsonPath("$[0].capacity").value(70));
        mockMvc.perform(get(URL_PREFIX + "/getShips")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[1].name").value("Maria"))
                .andExpect(jsonPath("$[1].id").value(4));

    }

    /*@Test
    @Transactional
    @WithMockUser(roles = {"SHIP"})
    public void testSaveShip() throws Exception {
        ShipDTO ship = new ShipDTO();
        ship.setName("newName");
        ship.setPrice(BigDecimal.valueOf(100));
        ship.setDescription("New description");
        ship.setAddress(new AddressDTO("street1", "city", "country"));
        ship.setOwnerId(1);
        ship.setAdditionalInfo("Additional info");
        ship.setLength(100.0);
        ship.setCancellationConditions("");
        ship.setCapacity(20);
        ship.setNumberOfEngines(2);
        ship.setPowerOfEngine(70);
        ship.setShipType(1);
        ship.setMaxSpeed(40);
        ship.setAddress(new AddressDTO("Street", "City", "Country"));
        /*ship.setNavigationEquipmentList(new ArrayList<>());
        ship.setFishingEquipmentList(new ArrayList<>());
        ship.setRules(new ArrayList<>());
        ship.setImagePaths(new ArrayList<>());
        ship.setAvailableEnd(LocalDateTime.now());
        ship.setAvailableEnd(LocalDateTime.now().plusDays(50));

        String json = TestUtil.json(ship);
        this.mockMvc.perform(post(URL_PREFIX + "/addShip").contentType(contentType).content(json)).
                andExpect(status().isCreated());
    }*/

    @Test
    @Transactional
    @WithMockUser(roles = {"SHIP"})
    public void testFilter() throws Exception
    {
        UserFilterDTO u = new UserFilterDTO();
        u.setCountry("Srbija");
        u.setCity("");
        u.setNumberOfPeople(1);
        u.setSortBy(0);
        ArrayList<String> sortByList = new ArrayList<>();
        sortByList.add("Name");
        sortByList.add("Rate");
        sortByList.add("Country");
        u.setSortByList(sortByList);

        String json = TestUtil.json(u);
        this.mockMvc.perform(post(URL_PREFIX + "/filter").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].name").value("Sirena"))
                .andExpect(jsonPath("$[0].powerOfEngine").value(100))
                .andExpect(jsonPath("$[0].maxSpeed").value(40))
                .andExpect(jsonPath("$[0].rate").value(10.0));

        UserFilterDTO u2 = new UserFilterDTO();
        u2.setCountry("");
        u2.setCity("Venecija");
        u2.setNumberOfPeople(1);
        u2.setSortBy(1);
        ArrayList<String> sortByList2 = new ArrayList<>();
        sortByList2.add("Name");
        sortByList2.add("Rate");
        sortByList2.add("Country");
        u2.setSortByList(sortByList2);

        String json2 = TestUtil.json(u2);
        this.mockMvc.perform(post(URL_PREFIX + "/filter").contentType(contentType).content(json2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].name").value("Ariel"))
                .andExpect(jsonPath("$[0].powerOfEngine").value(120))
                .andExpect(jsonPath("$[0].maxSpeed").value(35))
                .andExpect(jsonPath("$[0].capacity").value(50));
    }

    @Test
    @Transactional
    @WithMockUser(roles = {"SHIP"})
    public void testDeleteCottage() throws Exception {
        mockMvc.perform(delete(URL_PREFIX + "/deleteShip/4")).andExpect(status().isOk());
    }
}
