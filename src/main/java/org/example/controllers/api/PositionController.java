package org.example.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.PositionDTO;
import org.example.entities.Position;
import org.example.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/position")
@RequiredArgsConstructor
@Tag(name = "Position Controller", description = "API for control position information")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public List<PositionDTO> findAll(){
        return positionService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PositionDTO positionDTO){
        return ResponseEntity.ok(positionService.add(positionDTO));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<PositionDTO> result = positionService.findById(id);

        if(result.isPresent()){
            positionService.deleteById(id);

            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
