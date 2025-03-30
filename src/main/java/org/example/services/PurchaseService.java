package org.example.services;

import org.example.dto.GoodsDTO;
import org.example.dto.PurchaseDTO;
import org.example.entities.Purchase;
import org.example.mapping.PurchaseMapper;
import org.example.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<PurchaseDTO> findAll(){
        return purchaseRepository.findAll().stream().map(purchaseMapper::toDto).collect(Collectors.toList());
    }

    public PurchaseDTO save(PurchaseDTO data){
        Purchase result = purchaseRepository.save(purchaseMapper.toEntity(data));

        return purchaseMapper.toDto(result);
    }

    public Optional<PurchaseDTO> findById(Long id){
        return purchaseRepository.findById(id).map(purchaseMapper::toDto);
    }

    public void deleteById(Long id){
        purchaseRepository.deleteById(id);
    }

    public Map<String, Object> convertToMap(PurchaseDTO dto) {
        return purchaseMapper.toMap(dto);
    }
}
