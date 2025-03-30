package org.example.services;

import org.example.dto.GoodsDTO;
import org.example.dto.PurchaseTypeDTO;
import org.example.entities.PurchaseType;
import org.example.mapping.PurchaseTypeMapper;
import org.example.repositories.PurchaseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseTypeService {
    @Autowired
    private PurchaseTypeMapper purchaseTypeMapper;

    @Autowired
    private PurchaseTypeRepository purchaseTypeRepository;

    public List<PurchaseTypeDTO> findAll(){
        return purchaseTypeRepository.findAll().stream().map(purchaseTypeMapper::toDto).collect(Collectors.toList());
    }

    public PurchaseTypeDTO save(PurchaseTypeDTO dto){
        PurchaseType entity = purchaseTypeRepository.save(purchaseTypeMapper.toEntity(dto));

        return purchaseTypeMapper.toDto(entity);
    }

    public Optional<PurchaseTypeDTO> findById(Long id){
        return purchaseTypeRepository.findById(id).map(purchaseTypeMapper::toDto);
    }

    public void deleteById(Long id){
        purchaseTypeRepository.deleteById(id);
    }

    public Map<String, Object> convertToMap(PurchaseTypeDTO dto) {
        return purchaseTypeMapper.toMap(dto);
    }

    public List<Pair<Long, String>> findAllIdsWithName(){
        return purchaseTypeRepository.findAll()
                .stream()
                .map(p -> Pair.of(p.getId(), Objects.isNull(p.getName()) ? "undefined" : p.getName()))
                .collect(Collectors.toList());
    }
}
