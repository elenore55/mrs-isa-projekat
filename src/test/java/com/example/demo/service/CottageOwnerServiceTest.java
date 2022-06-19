package com.example.demo.service;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.repository.CottageOwnerRepository;
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
public class CottageOwnerServiceTest {
    @Mock
    private CottageOwnerRepository cottageOwnerRepositoryMock;

    @Mock
    private CottageOwner cottageOwner;

    @InjectMocks
    private CottageOwnerService cottageOwnerService;

    @Test
    public void testFindOne() {
        when(cottageOwnerRepositoryMock.findById(2)).thenReturn(Optional.of(cottageOwner));

        CottageOwner owner1 = cottageOwnerService.findOne(2);
        assertEquals(cottageOwner, owner1);

        verify(cottageOwnerRepositoryMock, times(1)).findById(2);
        verifyNoMoreInteractions(cottageOwnerRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testFindOneException() {
        when(cottageOwnerRepositoryMock.findById(2)).thenReturn(Optional.of(cottageOwner));

        CottageOwner owner2 = cottageOwnerService.findOne(100);
        assertNull(owner2);

        verify(cottageOwnerRepositoryMock, times(1)).findById(100);
        verifyNoMoreInteractions(cottageOwnerRepositoryMock);
    }

    @Test
    public void testSearchCottages() {
        when(cottageOwnerRepositoryMock.findById(2)).thenReturn(Optional.of(cottageOwner));

        Cottage c1 = new Cottage();
        c1.setName("c1");
        c1.setDescription("this is description");
        c1.setAdditionalInfo("info1");
        c1.setAddress(new Address("str1", "city1", "country1"));
        Cottage c2 = new Cottage();
        c2.setName("c2");
        c2.setDescription("desc2");
        c2.setAdditionalInfo("info2");
        c2.setAddress(new Address("str1", "city1", "country1"));
        Cottage c3 = new Cottage();
        c3.setName("c3");
        c3.setDescription("desc3");
        c3.setAdditionalInfo("info3");
        c3.setAddress(new Address("str1", "city1", "country1"));
        List<Cottage> mocks = new ArrayList<>();
        mocks.add(c1);
        mocks.add(c2);
        mocks.add(c3);

        when(cottageOwner.getCottages()).thenReturn(mocks);

        String search1 = "this";
        String search2 = "c";
        String search3 = "non existent";

        List<Cottage> cottages1 = cottageOwnerService.searchCottages(2, search1);
        List<Cottage> cottages2 = cottageOwnerService.searchCottages(2, search2);
        List<Cottage> cottages3 = cottageOwnerService.searchCottages(2, search3);

        assertThat(cottages1).hasSize(1);
        assertThat(cottages2).hasSize(3);
        assertThat(cottages3).hasSize(0);

        verify(cottageOwnerRepositoryMock, times(3)).findById(2);
        verifyNoMoreInteractions(cottageOwnerRepositoryMock);
    }

    @Test
    public void testFilterCottages() {
        when(cottageOwnerRepositoryMock.findById(2)).thenReturn(Optional.of(cottageOwner));

        Cottage c1 = new Cottage();
        c1.setName("c1");
        c1.setDescription("this is description");
        c1.setAdditionalInfo("info1");
        c1.setAddress(new Address("str1", "city1", "country1"));
        c1.setPriceList(BigDecimal.valueOf(100));
        Cottage c2 = new Cottage();
        c2.setName("c2");
        c2.setDescription("desc2");
        c2.setAdditionalInfo("info2");
        c2.setAddress(new Address("str1", "city2", "country2"));
        c2.setPriceList(BigDecimal.valueOf(200));
        Cottage c3 = new Cottage();
        c3.setName("c3");
        c3.setDescription("desc3");
        c3.setAdditionalInfo("info3");
        c3.setAddress(new Address("str1", "city3", "country3"));
        c3.setPriceList(BigDecimal.valueOf(300));
        List<Cottage> mocks = new ArrayList<>();
        mocks.add(c1);
        mocks.add(c2);
        mocks.add(c3);

        when(cottageOwner.getCottages()).thenReturn(mocks);

        FilterCottageDTO dto1 = new FilterCottageDTO();
        List<String> cities1 = new ArrayList<>();
        cities1.add("city2");
        cities1.add("city3");
        dto1.setCities(cities1);
        dto1.setLow(150);
        dto1.setHigh(250);
        dto1.setSortParam("price");
        dto1.setSortDir("ascending");

        FilterCottageDTO dto2 = new FilterCottageDTO();
        List<String> cities2 = new ArrayList<>();
        cities2.add("city1");
        cities2.add("city3");
        dto2.setCities(cities2);
        dto2.setLow(150);
        dto2.setHigh(250);
        dto2.setSortParam("price");
        dto2.setSortDir("ascending");

        FilterCottageDTO dto3 = new FilterCottageDTO();
        dto3.setLow(50);
        dto3.setHigh(350);
        dto3.setSortParam("city");
        dto3.setSortDir("descending");

        List<Cottage> cottages1 = cottageOwnerService.filterCottages(2, dto1);
        List<Cottage> cottages2 = cottageOwnerService.filterCottages(2, dto2);
        List<Cottage> cottages3 = cottageOwnerService.filterCottages(2, dto3);

        assertThat(cottages1).hasSize(1);
        assertThat(cottages2).hasSize(0);
        assertThat(cottages3).hasSize(3);
        assertEquals(cottages3.get(0).getAddress().getCity(), "city3");
        assertEquals(cottages3.get(1).getAddress().getCity(), "city2");
        assertEquals(cottages3.get(2).getAddress().getCity(), "city1");

        verify(cottageOwnerRepositoryMock, times(3)).findById(2);
        verifyNoMoreInteractions(cottageOwnerRepositoryMock);
    }
}
