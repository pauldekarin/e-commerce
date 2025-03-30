package org.example.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PurchaseDTO;
import org.example.dto.PurchaseTypeDTO;
import org.example.entities.Employee;
import org.example.entities.Purchase;
import org.example.entities.PurchaseType;
import org.example.repositories.EmployeeRepository;
import org.example.repositories.GoodsRepository;
import org.example.repositories.PurchaseTypeRepository;
import org.example.repositories.ShopRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class PurchaseMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private PurchaseTypeRepository purchaseTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> toMap(PurchaseDTO dto){
        return Objects.isNull(dto) ? null : objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
    }

    public Purchase toEntity(PurchaseDTO dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Purchase.class);
    }

    public PurchaseDTO toDto(Purchase entity){
        return Objects.isNull(entity) ? null : mapper.map(entity, PurchaseDTO.class);
    }

    @PostConstruct
    private void postConstruct(){
        mapper.createTypeMap(PurchaseDTO.class, Purchase.class)
                .addMappings(m -> m.skip(Purchase::setPurchaseType))
                .addMappings(m -> m.skip(Purchase::setShop))
                .addMappings(m -> m.skip(Purchase::setGoods))
                .addMappings(m -> m.skip(Purchase::setShop))
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(Purchase.class, PurchaseDTO.class)
                .addMappings(m -> m.skip(PurchaseDTO::setPurchaseTypeId))
                .addMappings(m -> m.skip(PurchaseDTO::setEmployeeId))
                .addMappings(m -> m.skip(PurchaseDTO::setGoodsId))
                .addMappings(m -> m.skip(PurchaseDTO::setShopId))
                .setPostConverter(toDtoConverter());
    }

    Converter<PurchaseDTO, Purchase> toEntityConverter(){
        return context -> {
            PurchaseDTO source = context.getSource();
            Purchase destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<Purchase, PurchaseDTO> toDtoConverter(){
        return context -> {
            Purchase source = context.getSource();
            PurchaseDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(PurchaseDTO source, Purchase destination){
        destination.setShop(shopRepository.findById(source.getShopId()).orElseThrow());
        destination.setPurchaseType(purchaseTypeRepository.findById(source.getPurchaseTypeId()).orElseThrow());
        destination.setGoods(goodsRepository.findById(source.getGoodsId()).orElseThrow());
        destination.setEmployee(employeeRepository.findById(source.getEmployeeId()).orElseThrow());
    }

    void mapSpecificFields(Purchase source, PurchaseDTO destination){
        destination.setEmployeeId(source.getEmployee().getId());
        destination.setShopId(source.getShop().getId());
        destination.setPurchaseTypeId(source.getPurchaseType().getId());
        destination.setGoodsId(source.getGoods().getId());
    }
}
