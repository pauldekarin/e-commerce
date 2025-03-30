package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.PositionDTO;
import org.example.entities.Employee;
import org.example.entities.Position;
import org.example.mapping.PositionMapper;
import org.example.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionMapper positionMapper;

    public List<PositionDTO> findAll(){
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toDto)
                .collect(Collectors.toList());
    }

    public PositionDTO add(PositionDTO positionDTO){
        if(Objects.isNull(positionDTO.getName())){
            throw  new IllegalArgumentException("Position name can't be null");
        }

        if(positionRepository.existsByName(positionDTO.getName())){
            throw new IllegalArgumentException(String.format("Position with name '%s' already exists!", positionDTO.getName()));
        }

        Position result = positionRepository.save(positionMapper.toEntity(positionDTO));

        return positionMapper.toDto(result);
    }

    public Optional<PositionDTO> findById(Long id){
        return positionRepository.findById(id).map(positionMapper::toDto);
    }

    public List<Pair<Long, String>> findAllIdsWithName(){
        return positionRepository.findAll()
                .stream()
                .map(p -> Pair.of(p.getId(), Objects.isNull(p.getName()) ? "undefined" : p.getName()))
                .collect(Collectors.toList());
    }
    public List<Long> findAllIds(){
        return positionRepository.findAll().stream().map(Position::getId).collect(Collectors.toList());
    }

    public Map<String, Object> convertToMap(PositionDTO data){
        return positionMapper.toMap(data);
    }

    public void deleteById(Long id){
        positionRepository.deleteById(id);
    }
}
