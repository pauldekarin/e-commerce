package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.EmployeeDTO;
import org.example.entities.Employee;
import org.example.repositories.PositionRepository;
import org.example.repositories.ShopRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Employee toEntity(EmployeeDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Employee.class);
    }

    public EmployeeDTO toDto(Employee entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, EmployeeDTO.class);
    }

    @PostConstruct
    public void postConstruct(){
        mapper.createTypeMap(Employee.class, EmployeeDTO.class)
                .addMappings(m -> m.skip(EmployeeDTO::setPositionId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(EmployeeDTO.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setPosition))
                .setPostConverter(toEntityConverter());
    }

    Converter<Employee, EmployeeDTO> toDtoConverter(){
        return context -> {
            Employee source = context.getSource();
            EmployeeDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<EmployeeDTO, Employee> toEntityConverter(){
        return context -> {
            EmployeeDTO source = context.getSource();
            Employee destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(Employee source, EmployeeDTO destination){
        if(source.getPosition() == null){
            destination.setPositionId(-1L);
        }else{
            destination.setPositionId(source.getPosition().getId());
        }

        if(source.getShop() == null){
            destination.setShopId(-1L);
        }else{
            destination.setShopId(source.getShop().getId());
        }
    }

    void mapSpecificFields(EmployeeDTO source, Employee destination){
        destination.setPosition(positionRepository.findById(source.getPositionId()).orElse(null));
        destination.setShop(shopRepository.findById(source.getShopId()).orElse(null));
    }

    public Map<String,Object> toMap(EmployeeDTO dto) {
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }
}
