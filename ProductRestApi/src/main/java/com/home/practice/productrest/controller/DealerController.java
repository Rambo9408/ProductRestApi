package com.home.practice.productrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.home.practice.productrest.model.Address;
import com.home.practice.productrest.model.Dealer;
import com.home.practice.productrest.model.DealerAndAddressProjection;
import com.home.practice.productrest.service.DealerService;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {

    @Autowired
    private DealerService dservice;

    @PostMapping("/register")
    public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer) {
        try {
            Address address = dealer.getAddress();
            // Establish the bi-directional relationship
            address.setDealer(dealer);
            dealer.setAddress(address);

            Dealer registeredDealer = dservice.registerDealer(dealer);
            if (registeredDealer != null) {
                return ResponseEntity.ok("Registration successful");
            } else {
                return ResponseEntity.badRequest().body("Registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginDealer(@Validated @RequestBody Dealer dealer) {
        try {
            Boolean isAuthenticated = dservice.authenticateDealer(dealer.getEmail(), dealer.getPassword());
            
            return ResponseEntity.ok(isAuthenticated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
//https://localhost:8082/productrestapi/api/dealers/dealersinfo
    @GetMapping("/dealersinfo")
    public ResponseEntity<List<DealerAndAddressProjection>> getDealerInfo() {
        try {
            List<DealerAndAddressProjection> selectedFields = dservice.getDealerInfo();
            return ResponseEntity.ok(selectedFields);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/info")
    public ResponseEntity<List<DealerAndAddressProjection>> postDealerInfo() {
        try {
            List<DealerAndAddressProjection> selectedFields = dservice.getDealerInfo();
            return ResponseEntity.ok(selectedFields);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
