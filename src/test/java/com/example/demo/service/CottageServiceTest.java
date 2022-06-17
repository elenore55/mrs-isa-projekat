package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.ProfileData;
import com.example.demo.model.Reservation;
import com.example.demo.repository.CottageRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageServiceTest {

    @Mock
    private CottageRepository cottageRepositoryMock;

    @Mock
    private EmailSender emailSenderMock;

    @InjectMocks
    private CottageService cottageService;

    @Test
    public void testGetCottages() {
        Cottage c1 = new Cottage();
        c1.setName("c1");
        Cottage c2 = new Cottage();
        c2.setName("c2");
        Cottage c3 = new Cottage();
        c3.setName("c3");
        List<Cottage> mocks = new ArrayList<>();
        mocks.add(c1);
        mocks.add(c2);
        mocks.add(c3);

        when(cottageRepositoryMock.findAll()).thenReturn(mocks);
        List<Cottage> cottages = cottageService.getCottages();

        assertThat(cottages).hasSize(3);
        assertEquals(cottages.get(0).getName(), "c1");
        assertEquals(cottages.get(1).getName(), "c2");
        assertEquals(cottages.get(2).getName(), "c3");

        verify(cottageRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(cottageRepositoryMock);
    }

    @Test
    @Transactional
    public void testSave() {
        Cottage toSave = new Cottage();
        toSave.setName("my cottage");
        toSave.setDescription("desc1");
        toSave.setAdditionalInfo("info1");
        toSave.setPriceList(BigDecimal.valueOf(55));

        Cottage c1 = new Cottage();
        c1.setName("c1");
        Cottage c2 = new Cottage();
        c2.setName("c2");
        Cottage c3 = new Cottage();
        c3.setName("c3");
        List<Cottage> mocks = new ArrayList<>();
        mocks.add(c1);
        mocks.add(c2);
        mocks.add(c3);

        when(cottageRepositoryMock.findAll()).thenReturn(mocks);
        when(cottageRepositoryMock.save(toSave)).thenReturn(toSave);

        int sizeBeforeSave = cottageRepositoryMock.findAll().size();
        Cottage saved = cottageRepositoryMock.save(toSave);
        mocks.add(saved);
        when(cottageRepositoryMock.findAll()).thenReturn(mocks);

        List<Cottage> cottages = cottageService.getCottages();
        assertThat(cottages).hasSize(sizeBeforeSave + 1);
        saved = cottages.get(cottages.size() - 1);
        assertEquals(saved.getName(), "my cottage");
        assertEquals(saved.getDescription(), "desc1");
        assertEquals(saved.getAdditionalInfo(), "info1");

        assertThat(saved).isNotNull();
        verify(cottageRepositoryMock, times(2)).findAll();
        verify(cottageRepositoryMock, times(1)).save(toSave);
        verifyNoMoreInteractions(cottageRepositoryMock);
    }


    @Test
    public void checkReservationsTest() {
        Cottage c1 = new Cottage();
        Reservation r1 = new Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(2), new Client(), c1);
        List<Reservation> reservations1 = new ArrayList<>();
        reservations1.add(r1);
        c1.setReservations(reservations1);
        boolean isFree1 = cottageService.checkReservations(c1);
        assertFalse(isFree1);

        Cottage c2 = new Cottage();
        c2.setReservations(new ArrayList<>());
        boolean isFree2 = cottageService.checkReservations(c2);
        assertTrue(isFree2);

        Cottage c3 = new Cottage();
        Reservation r2 = new Reservation(LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1), new Client(), c3);
        List<Reservation> reservations2 = new ArrayList<>();
        reservations1.add(r2);
        c3.setReservations(reservations2);
        boolean isFree3 = cottageService.checkReservations(c3);
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

        Cottage c1 = new Cottage();
        c1.setName("c1");
        List<Client> subscribers1 = new ArrayList<>();
        subscribers1.add(cli1);
        subscribers1.add(cli2);
        subscribers1.add(cli3);
        c1.setSubscribers(subscribers1);

        Cottage c2 = new Cottage();
        c2.setName("c2");
        List<Client> subscribers2 = new ArrayList<>();
        subscribers2.add(cli1);
        subscribers2.add(cli3);
        c2.setSubscribers(subscribers2);

        cottageService.notifySubscribers(c1);
        cottageService.notifySubscribers(c2);

        verify(emailSenderMock, times(1)).send("email1", "Subscription update", "There is a new fast reservation available for c1");
        verify(emailSenderMock, times(1)).send("email2", "Subscription update", "There is a new fast reservation available for c1");
        verify(emailSenderMock, times(1)).send("email3", "Subscription update", "There is a new fast reservation available for c1");
        verify(emailSenderMock, times(1)).send("email1", "Subscription update", "There is a new fast reservation available for c2");
        verify(emailSenderMock, times(1)).send("email3", "Subscription update", "There is a new fast reservation available for c2");
        verifyNoMoreInteractions(emailSenderMock);
    }
}
