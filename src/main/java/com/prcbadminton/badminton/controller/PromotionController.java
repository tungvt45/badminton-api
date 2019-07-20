package com.prcbadminton.badminton.controller;

import com.prcbadminton.badminton.dto.PageDTO;
import com.prcbadminton.badminton.entities.Promotion;
import com.prcbadminton.badminton.repository.PromotionRepository;
import com.prcbadminton.badminton.services.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/promotions")
public class PromotionController {
    private PromotionService promotionService;
    private PromotionRepository repository;

    public PromotionController(PromotionService promotionService, PromotionRepository repository) {
        this.promotionService = promotionService;
        this.repository = repository;
    }
    @GetMapping("/")
    public PageDTO<Promotion> getAll(@RequestParam Integer page, @RequestParam Integer element) {
        return this.promotionService.getAllPromotion(page, element);
    }
    @PostMapping
    public ResponseEntity create(@RequestBody Promotion promotion) {
        try {
            promotionService.save(promotion);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Promotion promotion) {
        try {
            promotionService.save(promotion);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
