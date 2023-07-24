package com.bms.schoolmanagementsystem.controller;

import com.bms.schoolmanagementsystem.dto.AddressDto;
import com.bms.schoolmanagementsystem.dto.request.address.CreateAddressRequest;
import com.bms.schoolmanagementsystem.dto.request.address.UpdateAddressRequest;
import com.bms.schoolmanagementsystem.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Void> createAddress(@Valid CreateAddressRequest request) {
        addressService.createAddress(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable String id,
                                              @Valid UpdateAddressRequest request) {
        addressService.updateAddress(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findAddressById(@PathVariable String id) {
        return ResponseEntity.ok(addressService.findAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddresses());
    }
}
