package org.example.controllers.api;

import org.example.dto.GoodsTypeDTO;
import org.example.services.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goods_type")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping
    public ResponseEntity<List<GoodsTypeDTO>> get(){
        return ResponseEntity.ok(goodsTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody GoodsTypeDTO dto){
        return ResponseEntity.ok(goodsTypeService.save(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<GoodsTypeDTO> result = goodsTypeService.findById(id);

        if(result.isPresent()){
            goodsTypeService.deleteById(id);

            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }
}
