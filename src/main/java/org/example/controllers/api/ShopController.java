package org.example.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.ShopDTO;
import org.example.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/shop")
@RestController
@Tag(name = "Shop Controller", description = "API for control Shop information")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/")
    public ResponseEntity<List<ShopDTO>> findAll(){
        return ResponseEntity.ok(shopService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ShopDTO data){
        return ResponseEntity.ok(shopService.save(data));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<ShopDTO> result = shopService.findById(id);

        if(result.isPresent()){
            shopService.deleteById(id);

            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
