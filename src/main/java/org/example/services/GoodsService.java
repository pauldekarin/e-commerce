package org.example.services;

import org.example.dto.GoodsDTO;
import org.example.dto.ShopDTO;
import org.example.entities.Goods;
import org.example.mapping.GoodsMapper;
import org.example.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsDTO> findAll(){
        return goodsRepository.findAll()
                .stream()
                .map(goodsMapper::toDto)
                .collect(Collectors.toList());
    }

    public GoodsDTO save(GoodsDTO goodsDto){
        Goods result = goodsRepository.save(goodsMapper.toEntity(goodsDto));

        return goodsMapper.toDto(result);
    }

    public Optional<GoodsDTO> findById(Long id){
        return goodsRepository.findById(id).map(goodsMapper::toDto);
    }

    public void deleteById(Long id){
        goodsRepository.deleteById(id);
    }

    public Map<String, Object> convertToMap(GoodsDTO dto) {
        return goodsMapper.toMap(dto);
    }

    public List<Pair< Long, String >> findAllIdsWithName(){
        return goodsRepository.findAll()
                .stream()
                .map( p -> Pair.of( p.getId(), Objects.isNull( p.getName() ) ? "undefined" : p.getName()) )
                .collect(Collectors.toList());
    }
}
