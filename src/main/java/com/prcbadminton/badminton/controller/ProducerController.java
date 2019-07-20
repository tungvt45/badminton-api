package com.prcbadminton.badminton.controller;

import com.prcbadminton.badminton.dto.PageDTO;
import com.prcbadminton.badminton.entities.Producer;
import com.prcbadminton.badminton.repository.ProducerRepository;
import com.prcbadminton.badminton.services.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/producers")
public class ProducerController {
    private ProducerRepository producerRepository;
    private ProducerService producerService;

    public ProducerController(ProducerRepository producerRepository, ProducerService producerService) {
        this.producerRepository = producerRepository;
        this.producerService = producerService;
    }
    @GetMapping("/")
    public PageDTO<Producer> getAll(@RequestParam Integer page, @RequestParam Integer element) {
        return this.producerService.getAllProducer(page, element);
    }
    @PostMapping
    public ResponseEntity create(@RequestBody Producer producer) {
        try {
            producerService.save(producer);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Producer producer) {
        try {
            producerService.save(producer);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
