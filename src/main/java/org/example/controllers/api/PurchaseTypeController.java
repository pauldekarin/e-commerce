package org.example.controllers.api;

import org.example.dto.PurchaseTypeDTO;
import org.example.entities.PurchaseType;
import org.example.services.PurchaseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase_type")
public class PurchaseTypeController {
    @Autowired
    private PurchaseTypeService purchaseTypeService;

    @GetMapping("/")
    public ResponseEntity<List<PurchaseTypeDTO>> get(){
        return ResponseEntity.ok(purchaseTypeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PurchaseTypeDTO data){
        return ResponseEntity.ok(purchaseTypeService.save(data));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<PurchaseTypeDTO> result = purchaseTypeService.findById(id);

        if(result.isPresent()){
           purchaseTypeService.deleteById(id);

           return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
