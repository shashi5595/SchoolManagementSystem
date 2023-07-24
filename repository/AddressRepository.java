package com.bms.schoolmanagementsystem.repository;

import com.bms.schoolmanagementsystem.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}