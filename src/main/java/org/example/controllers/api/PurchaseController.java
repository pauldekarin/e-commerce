package org.example.controllers.api;

import org.apache.coyote.Response;
import org.example.dto.PurchaseDTO;
import org.example.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/")
    public ResponseEntity<List<PurchaseDTO>> get(){
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PurchaseDTO purchaseDto){
        try {
            PurchaseDTO result = purchaseService.save(purchaseDto);

            return ResponseEntity.ok(result);
        }catch (NoSuchElementException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<PurchaseDTO> result = purchaseService.findById(id);

        if(result.isPresent()){
            purchaseService.deleteById(id);

            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
