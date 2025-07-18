package com.nexos.inventory.web.controller;

import com.nexos.inventory.domain.dto.merchandise.MerchandiseCreateDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseUpdateDto;
import com.nexos.inventory.domain.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/merchandise")
@CrossOrigin(origins = "http://localhost:4200")
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @GetMapping
    public ResponseEntity<List<MerchandiseDto>> getAllMerchandise() {
        List<MerchandiseDto> merchandiseDtos = merchandiseService.getAllMerchandise();
        return ResponseEntity.ok(merchandiseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchandiseDto> getMerchandiseById(@PathVariable Long id) {
        MerchandiseDto merchandiseDto = merchandiseService.getMerchandiseById(id);
        return ResponseEntity.ok(merchandiseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MerchandiseDto>> searchMerchandise(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate entryDate) {

        System.out.println("DATE: " + entryDate);
        System.out.println("NAME: " + productName);
        System.out.println("USER: " + userId);
                List<MerchandiseDto> merchandiseDtos = merchandiseService.getMerchandiseSearch(productName, userId, entryDate);
                return ResponseEntity.ok(merchandiseDtos);
    }

    @PostMapping
    public ResponseEntity<MerchandiseDto> createMerchandise(@RequestBody MerchandiseCreateDto createDto) {
        MerchandiseDto merchandiseDto = merchandiseService.createMerchandise(createDto);
        return new ResponseEntity<>(merchandiseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchandiseDto> updateMerchandise(@PathVariable Long id,
            @RequestBody MerchandiseUpdateDto updateDto) {
        MerchandiseDto updateMerchandise = merchandiseService.updateMerchandise(id, updateDto);
        return ResponseEntity.ok(updateMerchandise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchandise(@PathVariable Long id, @RequestParam Long userId) {
        merchandiseService.deleteMerchandise(id, userId);
        return ResponseEntity.noContent().build();
    }

}
