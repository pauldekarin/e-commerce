package org.example.services;

import org.example.dto.GoodsDTO;
import org.example.dto.GoodsTypeDTO;
import org.example.entities.GoodsType;
import org.example.mapping.GoodsTypeMapper;
import org.example.repositories.GoodsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsTypeService {
    @Autowired
    private GoodsTypeRepository goodsTypeRepository;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    public List<GoodsTypeDTO> findAll(){
        return goodsTypeRepository.findAll().stream().map(goodsTypeMapper::toDto).collect(Collectors.toList());
    }

    public GoodsTypeDTO save(GoodsTypeDTO data){
        GoodsType result = goodsTypeRepository.save(goodsTypeMapper.toEntity(data));

        return goodsTypeMapper.toDto(result);
    }

    public Optional<GoodsTypeDTO> findById(Long id){
        return goodsTypeRepository.findById(id).map(goodsTypeMapper::toDto);
    }

    public void deleteById(Long id){
        goodsTypeRepository.deleteById(id);
    }

    public Map<String, Object> convertToMap(GoodsTypeDTO dto) {
        return goodsTypeMapper.toMap(dto);
    }

    public List<Pair< Long, String >> findAllIdsWithName(){
        return goodsTypeRepository.findAll()
                .stream()
                .map( p -> Pair.of( p.getId(), Objects.isNull(p.getName()) ? "undefined" : p.getName() ) )
                .collect(Collectors.toList());
    }
}
