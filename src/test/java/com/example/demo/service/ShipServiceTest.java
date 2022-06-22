package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.CottageRepository;
import com.example.demo.repository.ShipRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ShipServiceTest {

    @Mock
    private ShipRepository shipRepositoryMock;

    @Mock
    private EmailSender emailSenderMock;

    @InjectMocks
    private ShipService shipService;

    @Test
    public void testGetCottages() {
        Ship s1 = new Ship();
        Ship s2 = new Ship();
        Ship s3 = new Ship();
        s1.setName("Prvi brod");
        s2.setName("Drugi brod");
        s3.setName("Treci brod");

        List<Ship> mocks = new ArrayList<>();
        mocks.add(s1);
        mocks.add(s2);
        mocks.add(s3);

        when(shipRepositoryMock.findAll()).thenReturn(mocks);
        List<Ship> ships = shipService.getShips();

        assertThat(ships).hasSize(3);
        assertEquals(ships.get(0).getName(), "Prvi brod");
        assertEquals(ships.get(1).getName(), "Drugi brod");
        assertEquals(ships.get(2).getName(), "Treci brod");

        verify(shipRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(shipRepositoryMock);
    }

    @Test
    @Transactional
    public void testSave() {
        Ship toSave = new Ship();
        toSave.setName("my ship");
        toSave.setDescription("desc1");
        toSave.setAdditionalInfo("info1");
        toSave.setPriceList(BigDecimal.valueOf(55));

        Ship s1 = new Ship();
        Ship s2 = new Ship();
        Ship s3 = new Ship();
        s1.setName("Prvi brod");
        s2.setName("Drugi brod");
        s3.setName("Treci brod");

        List<Ship> mocks = new ArrayList<>();
        mocks.add(s1);
        mocks.add(s2);
        mocks.add(s3);

        when(shipRepositoryMock.findAll()).thenReturn(mocks);
        when(shipRepositoryMock.save(toSave)).thenReturn(toSave);

        int sizeBeforeSave = shipRepositoryMock.findAll().size();
        Ship saved = shipRepositoryMock.save(toSave);
        mocks.add(saved);
        when(shipRepositoryMock.findAll()).thenReturn(mocks);

        List<Ship> ships = shipService.getShips();
        assertThat(ships).hasSize(sizeBeforeSave + 1);
        saved = ships.get(ships.size() - 1);
        assertEquals(saved.getName(), "my ship");
        assertEquals(saved.getDescription(), "desc1");
        assertEquals(saved.getAdditionalInfo(), "info1");

        assertThat(saved).isNotNull();
        verify(shipRepositoryMock, times(2)).findAll();
        verify(shipRepositoryMock, times(1)).save(toSave);
        verifyNoMoreInteractions(shipRepositoryMock);
    }


    @Test
    public void checkReservationsTest() {
        Ship s1 = new Ship();
        Reservation r1 = new Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(2), new Client(), s1);
        List<Reservation> reservations1 = new ArrayList<>();
        reservations1.add(r1);
        s1.setReservations(reservations1);
        boolean isFree1 = shipService.checkReservations(s1);
        assertFalse(isFree1);

        Ship s2 = new Ship();
        s2.setReservations(new ArrayList<>());
        boolean isFree2 = shipService.checkReservations(s2);
        assertTrue(isFree2);

        Ship s3 = new Ship();
        Reservation r2 = new Reservation(LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1), new Client(), s3);
        List<Reservation> reservations2 = new ArrayList<>();
        reservations1.add(r2);
        s3.setReservations(reservations2);
        boolean isFree3 = shipService.checkReservations(s3);
        assertTrue(isFree3);
    }

    @Test
    public void notifySubscribersTest() {
        Client cli1 = new Client();
        cli1.setProfileData(new ProfileData());
        cli1.setEmail("email1");
        Client cli2 = new Client();
        cli2.setProfileData(new ProfileData());
        cli2.setEmail("email2");
        Client cli3 = new Client();
        cli3.setProfileData(new ProfileData());
        cli3.setEmail("email3");

        Ship s1 = new Ship();
        s1.setName("s1");
        List<Client> subscribers1 = new ArrayList<>();
        subscribers1.add(cli1);
        subscribers1.add(cli2);
        subscribers1.add(cli3);
        s1.setSubscribers(subscribers1);

        Ship s2 = new Ship();
        s2.setName("s2");
        List<Client> subscribers2 = new ArrayList<>();
        subscribers2.add(cli1);
        subscribers2.add(cli3);
        s2.setSubscribers(subscribers2);

        shipService.notifySubscribers(s1);
        shipService.notifySubscribers(s2);

        verify(emailSenderMock, times(1)).send("email1", "Subscription update", "There is a new fast reservation available for s1");
        verify(emailSenderMock, times(1)).send("email2", "Subscription update", "There is a new fast reservation available for s1");
        verify(emailSenderMock, times(1)).send("email3", "Subscription update", "There is a new fast reservation available for s1");
        verify(emailSenderMock, times(1)).send("email1", "Subscription update", "There is a new fast reservation available for s2");
        verify(emailSenderMock, times(1)).send("email3", "Subscription update", "There is a new fast reservation available for s2");
        verifyNoMoreInteractions(emailSenderMock);
    }
}
