package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select distinct a.city from Address a")
    public List<String> getCities();

    @Query("select distinct a.country from Address a")
    public List<String> getCountries();
}
