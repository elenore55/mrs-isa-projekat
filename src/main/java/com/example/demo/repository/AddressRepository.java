package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select distinct a.city from Address a")
    public List<String> getCities();

    @Query("select distinct a.country from Address a")
    public List<String> getCountries();

    @Modifying
    @Query("update Address a set a.street = ?1, a.city = ?2, a.country = ?3  where a.id = ?4")
    void updateAddress(String street, String city, String country, int addressId);

    @Query("select a from Address a where a.street = ?1 and a.city = ?2 and a.country = ?3")
    Address getExistingAddress(String street, String city, String country);
}
