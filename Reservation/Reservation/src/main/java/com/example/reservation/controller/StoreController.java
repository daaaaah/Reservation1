package com.example.reservation.controller;

import com.example.reservation.dto.StoreDTO;
import com.example.reservation.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;
    private StoreDTO storeDTO;

    @PostMapping
    public StoreDTO createStore(@RequestBody StoreDTO storeDTO) {
        return storeService.registerStore(storeDTO);
    }

    @GetMapping("/{id}")
    public StoreDTO getStore(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

}