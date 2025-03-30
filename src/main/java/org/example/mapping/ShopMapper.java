package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ShopDTO;
import org.example.entities.Shop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class ShopMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public Shop toEntity(ShopDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Shop.class);
    }

    public ShopDTO toDto(Shop entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, ShopDTO.class);
    }

    public Map<String, Object> toMap(ShopDTO dto) {
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }

    @PostConstruct
    public void postConstruct(){
        mapper.createTypeMap(ShopDTO.class, Shop.class)
                .addMappings(m -> m.skip(Shop::setId));
    }
}
