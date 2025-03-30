package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PurchaseTypeDTO;
import org.example.entities.PurchaseType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class PurchaseTypeMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> toMap(PurchaseTypeDTO dto){
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }

    public PurchaseType toEntity(PurchaseTypeDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, PurchaseType.class);
    }

    public PurchaseTypeDTO toDto(PurchaseType entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, PurchaseTypeDTO.class);
    }
}
