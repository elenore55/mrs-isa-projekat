package com.example.demo.service;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.Category;
import com.example.demo.repository.*;
import com.example.demo.service.emailSenders.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository shipRepositoryMock;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Profile_DataRepository profileDataRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetClientByEmail() {
        Client c1 = new Client();
        c1.setCategory(Category.GOLD);
        ProfileData pd1 = new ProfileData();
        pd1.setEmail("m@m");
        pd1.setName("Marko");
        pd1.setSurname("Markovic");
        pd1.setId(1);
        when(profileDataRepository.getByEmail("m@m")).thenReturn(pd1);
        when(clientRepository.findByProfileDataId(1)).thenReturn(c1);
        assertEquals(userService.findClientByEmail("m@m").getCategory(), Category.GOLD);

        Client c2 = new Client();
        c2.setCategory(Category.SILVER);
        ProfileData pd2 = new ProfileData();
        pd2.setEmail("marko@m");
        pd2.setName("Marko");
        pd2.setSurname("Markovic");
        pd2.setId(17);
        when(profileDataRepository.getByEmail("marko@m")).thenReturn(pd2);
        when(clientRepository.findByProfileDataId(17)).thenReturn(c2);
        assertEquals(userService.findClientByEmail("marko@m").getCategory(), Category.SILVER);
    }

    @Test
    public void alreadyRegisteredTest() {
        ProfileData pd1 = new ProfileData();
        pd1.setEmail("m@m");
        pd1.setName("Marko");
        pd1.setSurname("Markovic");
        pd1.setId(1);

        ProfileData pd2 = new ProfileData();
        pd2.setEmail("milana@gmail.com");
        pd2.setName("Milana");
        pd2.setSurname("Stankovic");
        pd2.setId(11);

        when(profileDataRepository.getByEmail("m@m")).thenReturn(pd1);
        when(profileDataRepository.getByEmail("milana@gmail.com")).thenReturn(pd2);
        assertTrue(userService.isAlreadyRegistered("m@m"));
        assertTrue(userService.isAlreadyRegistered("milana@gmail.com"));
        assertFalse(userService.isAlreadyRegistered("marko@gmail.com"));
    }

    @Test
    public void testValidPasswords() {
        String pass1 = "oldPass";
        String pass2 = "newPass";
        String pass3 = "$2a$10$eJAhTBNusorL6jl1LxEuOeXwd75E1MV/XX8u67Fb/IO5yxpUmBOoC";
        String pass4 = "$TBNusorL6jl1LxEuOeXwd75E1MV/XX8u67Fb/IO5yxpUmBOoC2a$10$eJAh";
        String pass5 = "newPass";
        assertFalse(userService.isValidPassword(pass1, pass2));
        assertFalse(userService.isValidPassword(pass3, pass4));
        assertTrue(userService.isValidPassword(pass2, pass5));
    }
}
