package com.example.reservation.service;

import com.example.reservation.dto.StoreDTO;
import com.example.reservation.model.Store;
import com.example.reservation.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;


    //스토어의 ID를 기준으로 스토어의 세부 정보를 검색
    public StoreDTO getStoreById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
        return new StoreDTO(store.getId(), store.getName(), store.getLocation(), store.getOwner().getId());
    }

    //storeDTO에 제공된 정보를 이용하여 신규 스토어를 시스템에 등록
    public StoreDTO registerStore(StoreDTO storeDTO) {
        Store store = new Store();
        store.setName(storeDTO.getName());
        store = storeRepository.save(store);
        return convertToDTO(store);
    }

    private StoreDTO convertToDTO(Store store) {
        return new StoreDTO(store.getId(), store.getName(), store.getOwner().getId());
    }
}
