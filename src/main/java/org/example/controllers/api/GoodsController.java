package org.example.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.example.dto.GoodsDTO;
import org.example.entities.Goods;
import org.example.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goods")
@Tag(name = "Goods Controller")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/")
    public ResponseEntity<List<GoodsDTO>> get(){
        return ResponseEntity.ok(goodsService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody GoodsDTO data){
        return ResponseEntity.ok(goodsService.save(data));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<GoodsDTO> result = goodsService.findById(id);

        if(result.isPresent()){
            goodsService.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
