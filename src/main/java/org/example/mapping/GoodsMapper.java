package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.GoodsDTO;
import org.example.entities.Goods;
import org.example.repositories.GoodsTypeRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class GoodsMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GoodsTypeRepository goodsTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Goods toEntity(GoodsDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Goods.class);
    }

    public GoodsDTO toDto(Goods entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, GoodsDTO.class);
    }

    public Map<String, Object> toMap(GoodsDTO dto){
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }

    @PostConstruct
    public void postConstruct(){
        mapper.createTypeMap(GoodsDTO.class, Goods.class)
                .addMappings(m -> m.skip(Goods::setId))
                .addMappings(m -> m.skip(Goods::setGoodsType))
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(Goods.class, GoodsDTO.class)
                .addMappings(m -> m.skip(GoodsDTO::setGoodsTypeId))
                .setPostConverter(toDtoConverter());
    }

    Converter<GoodsDTO, Goods> toEntityConverter(){
        return context -> {
            GoodsDTO source = context.getSource();
            Goods destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<Goods, GoodsDTO> toDtoConverter(){
        return context -> {
            Goods source = context.getSource();
            GoodsDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(Goods source, GoodsDTO destination){
        destination.setGoodsTypeId(source.getGoodsType().getId());
    }

    void mapSpecificFields(GoodsDTO source, Goods destination){
        destination.setGoodsType(goodsTypeRepository.findById(source.getGoodsTypeId()).orElseThrow());
    }
}
