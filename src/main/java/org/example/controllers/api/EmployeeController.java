package org.example.controllers.api;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDTO;
import org.example.entities.Employee;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> get(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> add(@RequestBody EmployeeDTO employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<EmployeeDTO> result = employeeService.findById(id);

        if(result.isPresent()){
            employeeService.deleteById(id);

            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
