package com.example.demo.service;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.ShipDTO;
import com.example.demo.model.Address;
import com.example.demo.model.Ship;
import com.example.demo.model.ShipOwner;
import com.example.demo.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipOwnerService {
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    public ShipOwnerService(ShipOwnerRepository shipOwnerRepository) {
        this.shipOwnerRepository = shipOwnerRepository;
    }

    public ShipOwner findOne(Integer id) {
        return shipOwnerRepository.findById(id).orElseGet(null);
    }

    public ShipOwner save(ShipOwner shipOwner) {
        return this.shipOwnerRepository.save(shipOwner);
    }

    public List<ShipDTO> searchShips(Integer id, String search) {
        ShipOwner owner = findOne(id);
        List<Ship> ships = owner.getShips();
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            Address a = s.getAddress();
            if (s.getShipType().toString().toLowerCase().contains(search) || s.getName().toLowerCase().contains(search) || s.getDescription().toLowerCase().contains(search) ||
                s.getAdditionalInfo().toLowerCase().contains(search) || a.getStreet().toLowerCase().contains(search) || a.getCity().toLowerCase().contains(search) ||
                a.getCountry().toLowerCase().contains(search)) {
                dtos.add(new ShipDTO(s));
            }
        }
        return dtos;
    }

    public List<ShipDTO> filterShips(Integer id, FilterShipDTO filter) {
        ShipOwner owner = findOne(id);
        List<Ship> ships = owner.getShips();
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            Address a = s.getAddress();
            if (filter.checkCity(a.getCity()) && filter.checkCountry(a.getCountry()) && filter.checkPrice(s.getPriceList()) &&
            filter.checkLength(s.getLength()) && filter.checkCapacity(s.getCapacity()) && filter.checkSpeed(s.getMaxSpeed())) {
                dtos.add(new ShipDTO(s));
            }
        }
        return dtos;
    }

    private void sortShips(List<ShipDTO> ships, String sortBy, boolean asc) {

    }
}
