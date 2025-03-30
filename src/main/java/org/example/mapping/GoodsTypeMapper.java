package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.GoodsTypeDTO;
import org.example.entities.GoodsType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class GoodsTypeMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> toMap(GoodsTypeDTO dto){
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }

    public GoodsType toEntity(GoodsTypeDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, GoodsType.class);
    }

    public GoodsTypeDTO toDto(GoodsType entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, GoodsTypeDTO.class);
    }
}
