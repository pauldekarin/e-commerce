package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.example.dto.PositionDTO;
import org.example.entities.Position;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class PositionMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public Position toEntity(PositionDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Position.class);
    }

    public PositionDTO toDto(Position entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, PositionDTO.class);
    }

    public Map<String, Object> toMap(PositionDTO dto){
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>(){});
    }

    @PostConstruct
    public void postConstruct(){
        mapper.createTypeMap(PositionDTO.class, Position.class)
                .addMappings(m -> m.skip(Position::setId));
    }
}
