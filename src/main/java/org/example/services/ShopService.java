package org.example.services;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.example.dto.ShopDTO;
import org.example.entities.Shop;
import org.example.mapping.ShopMapper;
import org.example.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    public List<ShopDTO> findAll(){
        return shopRepository.findAll().stream().map(shopMapper::toDto).collect(Collectors.toList());
    }

    public ShopDTO save(ShopDTO shopDto){
        if(shopRepository.existsByName(shopDto.getName())){
            throw new IllegalArgumentException(String.format("Shop by name '%s' already exists", shopDto.getName()));
        }
        Shop result = shopRepository.save(shopMapper.toEntity(shopDto));

        return shopMapper.toDto(result);
    }

    public Optional<ShopDTO> findById(@NotNull Long id){
        return shopRepository.findById(id).map(shopMapper::toDto);
    }

    public void deleteById(Long id){
        shopRepository.deleteById(id);
    }

    public List<Pair<Long, String>> findAllIdsWithName(){
        return shopRepository.findAll()
                .stream()
                .map(p -> Pair.of(
                                        p.getId(),
                                        Objects.isNull(p.getName()) ? "undefined" : p.getName()
                                    ))
                .collect(Collectors.toList());
    }

    public Map<String, Object> convertToMap(ShopDTO dto){
        return shopMapper.toMap(dto);
    }
}
