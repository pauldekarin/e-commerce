package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDTO;
import org.example.entities.Employee;
import org.example.mapping.EmployeeMapper;
import org.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> findAll(){
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    public EmployeeDTO save(EmployeeDTO dto){
        Employee result = employeeRepository.save(employeeMapper.toEntity(dto));

        return employeeMapper.toDto(result);
    }

    public Optional<EmployeeDTO> findById(Long id){
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public Map<String, Object> convertToMap(EmployeeDTO dto) {return employeeMapper.toMap(dto);}

    public List<Pair<Long, String >> findAllIdsWithName(){
        return employeeRepository.findAll()
                .stream()
                .map(p -> Pair.of( p.getId(), Objects.isNull(p.getName()) ? "undefined" : p.getName() ))
                .collect(Collectors.toList());
    }
}
