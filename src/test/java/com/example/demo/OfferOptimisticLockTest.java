package com.example.demo;

import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.service.CottageService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferOptimisticLockTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        Cottage c1 = new Cottage();
        c1.setName("c1");
        c1.setDescription("d1");
        c1.setPriceList(BigDecimal.valueOf(50));
        //c1.setAddress(new Address("street1", "city1", "country1"));

        Cottage c2 = new Cottage();
        c2.setName("c1");
        c2.setDescription("d1");
        c2.setPriceList(BigDecimal.valueOf(50));
        //c2.setAddress(new Address("street1", "city1", "country1"));

        Cottage c3 = new Cottage();
        c3.setName("c1");
        c3.setDescription("d1");
        c3.setPriceList(BigDecimal.valueOf(50));
        //c3.setAddress(new Address("street1", "city1", "country1"));

        Cottage c4 = new Cottage();
        c4.setName("c1");
        c4.setDescription("d1");
        c4.setPriceList(BigDecimal.valueOf(50));
        //c4.setAddress(new Address("street1", "city1", "country1"));

        cottageService.save(c1);
        cottageService.save(c2);
        cottageService.save(c3);
        cottageService.save(c4);
    }

    @org.junit.Test(expected = OptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Cottage c = cottageService.findOne(2);
                System.out.println("VERSION3");
                System.out.println(c.getVersion());
                c.setPriceHistory(new ArrayList<>());
                c.setRules(new ArrayList<>());
                c.setImages(new ArrayList<>());
                c.incNumberOfReservations();
                try { Thread.sleep(3000); } catch (InterruptedException e) {}
                c = cottageService.save(c);
                System.out.println("VERSION4");
                System.out.println(c.getVersion());
                // List<Reservation> reservations = c.getReservations();
                // reservations.add(r1);
                // cottageService.save(c);
                // reservationService.findOne(1);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Cottage c = cottageService.findOne(2);
                c.setPriceHistory(new ArrayList<>());
                c.setRules(new ArrayList<>());
                c.setImages(new ArrayList<>());
                c.incNumberOfReservations();
                System.out.println("VERSION1");
                System.out.println(c.getVersion());
                c = cottageService.save(c);
                System.out.println("VERSION2");
                System.out.println(c.getVersion());
                // List<Reservation> reservations = c.getReservations();
                // reservations.add(r1);
                // cottageService.save(c);
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}