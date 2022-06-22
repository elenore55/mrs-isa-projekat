package com.example.demo.service;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.FilterShipDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.ShipType;
import com.example.demo.repository.CottageOwnerRepository;
import com.example.demo.repository.ShipOwnerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipOwnerServiceTest {
    @Mock
    private ShipOwnerRepository shipOwnerRepositoryMock;

    @Mock
    private ShipOwner shipOwner;

    @InjectMocks
    private ShipOwnerService shipOwnerService;

    @Test
    public void testFindOne() {
        when(shipOwnerRepositoryMock.findById(1)).thenReturn(Optional.of(shipOwner));

        ShipOwner owner1 = shipOwnerService.findOne(1);
        assertEquals(shipOwner, owner1);

        verify(shipOwnerRepositoryMock, times(1)).findById(1);
        verifyNoMoreInteractions(shipOwnerRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testFindOneException() {
        when(shipOwnerRepositoryMock.findById(1)).thenReturn(Optional.of(shipOwner));

        ShipOwner owner2 = shipOwnerService.findOne(12);
        assertNull(owner2);

        verify(shipOwnerRepositoryMock, times(1)).findById(2);
        verifyNoMoreInteractions(shipOwnerRepositoryMock);
    }

    @Test
    public void testSearchShips() {
        when(shipOwnerRepositoryMock.findById(1)).thenReturn(Optional.of(shipOwner));
        Ship s1 = new Ship();
        s1.setName("s1");
        s1.setDescription("Ovo je prostran brodic");
        s1.setAdditionalInfo("Dodatna informacija");
        s1.setAddress(new Address("str1", "city1", "country1"));
        s1.setShipType(ShipType.SHIP);

        Ship s2 = new Ship();
        s2.setName("s2");
        s2.setDescription("Ovo je drugi prostrani brodic 2");
        s2.setAdditionalInfo("Dodatna info 2");
        s2.setAddress(new Address("str2", "city2", "country2"));
        s2.setShipType(ShipType.SHIP);

        Ship s3 = new Ship();
        s3.setName("s3");
        s3.setDescription("Treci opis 3");
        s3.setAdditionalInfo("Dodatna info 3");
        s3.setAddress(new Address("str3", "city3", "country3"));
        s3.setShipType(ShipType.SHIP);

        List<Ship> mocks = new ArrayList<>();
        mocks.add(s1);
        mocks.add(s2);
        mocks.add(s3);

        when(shipOwner.getShips()).thenReturn(mocks);

        String search1 = "ovo";
        String search2 = "informacija";
        String search3 = "prazan skup";

        List<Ship> ships1 = shipOwnerService.searchShips(1, search1);
        List<Ship> ships2 = shipOwnerService.searchShips(1, search2);
        List<Ship> ships3 = shipOwnerService.searchShips(1, search3);

        assertThat(ships1).hasSize(2);
        assertThat(ships2).hasSize(1);
        assertThat(ships3).hasSize(0);

        verify(shipOwnerRepositoryMock, times(3)).findById(1);
        verifyNoMoreInteractions(shipOwnerRepositoryMock);
    }

    @Test
    public void testFilterShips() {
        when(shipOwnerRepositoryMock.findById(1)).thenReturn(Optional.of(shipOwner));

        Ship s1 = new Ship();
        s1.setName("s1");
        s1.setDescription("Ovo je prostran brodic");
        s1.setAdditionalInfo("Dodatna informacija");
        s1.setAddress(new Address("str1", "city1", "country1"));
        s1.setShipType(ShipType.SHIP);
        s1.setPriceList(BigDecimal.valueOf(100));
        s1.setLength(100.0);
        s1.setCapacity(100);
        s1.setMaxSpeed(50);

        Ship s2 = new Ship();
        s2.setName("s2");
        s2.setDescription("Ovo je drugi prostrani brodic 2");
        s2.setAdditionalInfo("Dodatna info 2");
        s2.setAddress(new Address("str2", "city2", "country2"));
        s2.setShipType(ShipType.SHIP);
        s2.setPriceList(BigDecimal.valueOf(200));
        s2.setLength(100.0);
        s2.setCapacity(100);
        s2.setMaxSpeed(50);

        Ship s3 = new Ship();
        s3.setName("s3");
        s3.setDescription("Treci opis 3");
        s3.setAdditionalInfo("Dodatna info 3");
        s3.setAddress(new Address("str3", "city3", "country3"));
        s3.setShipType(ShipType.SHIP);
        s3.setPriceList(BigDecimal.valueOf(300));
        s3.setLength(100.0);
        s3.setCapacity(100);
        s3.setMaxSpeed(50);

        List<Ship> mocks = new ArrayList<>();
        mocks.add(s1);
        mocks.add(s2);
        mocks.add(s3);

        when(shipOwner.getShips()).thenReturn(mocks);

        FilterShipDTO dto1 = new FilterShipDTO();
        List<String> cities1 = new ArrayList<>();
        cities1.add("city2");
        cities1.add("city3");
        dto1.setCities(cities1);
        dto1.setLowPrice(150);
        dto1.setHighPrice(250);
        dto1.setSortParam("price");
        dto1.setSortDir("ascending");

        FilterShipDTO dto2 = new FilterShipDTO();
        List<String> cities2 = new ArrayList<>();
        cities2.add("city1");
        cities2.add("city3");
        dto2.setCities(cities2);
        dto2.setLowPrice(150);
        dto2.setHighPrice(250);
        dto2.setSortParam("price");
        dto2.setSortDir("ascending");

        FilterShipDTO dto3 = new FilterShipDTO();
        dto3.setLowPrice(50);
        dto3.setHighPrice(350);
        dto3.setSortParam("city");
        dto3.setSortDir("descending");

        List<Ship> ships1 = shipOwnerService.filterShips(1, dto1);
        List<Ship> ships2 = shipOwnerService.filterShips(1, dto2);
        List<Ship> ships3 = shipOwnerService.filterShips(1, dto3);

        assertThat(ships1).hasSize(1);
        assertThat(ships2).hasSize(0);
        assertThat(ships3).hasSize(3);

        assertEquals(ships3.get(0).getAddress().getCity(), "city3");
        assertEquals(ships3.get(1).getAddress().getCity(), "city2");
        assertEquals(ships3.get(2).getAddress().getCity(), "city1");

        verify(shipOwnerRepositoryMock, times(3)).findById(1);
        verifyNoMoreInteractions(shipOwnerRepositoryMock);
    }
}
