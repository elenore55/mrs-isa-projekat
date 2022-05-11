package com.example.demo.service;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.comparators.cottage.*;
import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CottageOwnerService {
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    public CottageOwnerService(CottageOwnerRepository cottageOwnerRepository) {
        this.cottageOwnerRepository = cottageOwnerRepository;
    }

    public CottageOwner findOne(Integer id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public CottageOwner save(CottageOwner owner) {
        return this.cottageOwnerRepository.save(owner);
    }

    public List<Cottage> searchCottages(Integer id, String search) {
        CottageOwner owner = findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<Cottage> result = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (c.getName().toLowerCase().contains(search) || c.getDescription().toLowerCase().contains(search) || c.getAdditionalInfo().toLowerCase().contains(search) ||
                    a.getStreet().toLowerCase().contains(search) || a.getCity().toLowerCase().contains(search) || a.getCountry().toLowerCase().contains(search)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Cottage> filterCottages(Integer id, FilterCottageDTO filter) {
        CottageOwner owner = findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<Cottage> result = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (filter.checkCity(a.getCity()) && filter.checkCountry(a.getCountry()) && filter.checkPrice(c.getPriceList())) {
                result.add(c);
            }
        }
        sortCottages(result, filter.getSortParam().toLowerCase(), filter.getSortDir().equalsIgnoreCase("descending"));
        return result;
    }

    private void sortCottages(List<Cottage> cottages, String sortBy, boolean desc) {
        Comparator<Cottage> comparator = selectComparator(sortBy);
        if (desc) cottages.sort(Collections.reverseOrder(comparator));
        else cottages.sort(comparator);
    }

    private Comparator<Cottage> selectComparator(String sortBy) {
        switch (sortBy) {
            case "price":
                return new CottagePriceComparator();
            case "number of rooms":
                return new CottageRoomsComparator();
            case "rating":
                return new CottageRatingComparator();
            case "city":
                return new CottageCityComparator();
            case "country":
                return new CottageCountryComparator();
            default:
                return new CottageNameComparator();
        }
    }
}
