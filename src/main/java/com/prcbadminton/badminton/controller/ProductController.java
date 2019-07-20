package com.prcbadminton.badminton.controller;

import com.prcbadminton.badminton.dto.PageDTO;
import com.prcbadminton.badminton.dto.ProductDTO;
import com.prcbadminton.badminton.entities.Product;
import com.prcbadminton.badminton.repository.ProductRepository;
import com.prcbadminton.badminton.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ProductRepository repository;

    public ProductController(ProductService productService, ProductRepository repository) {
        this.productService = productService;
        this.repository = repository;
    }

    //    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/promotionProduct")
    public List<Product> getProductInHomePage() {
        return this.productService.getProductInHomePage();
    }

    @GetMapping("/bestSalesProduct")
    public List<Product> getBestSalesProduct() {
        return this.productService.getBestSalesProduct();
    }

    @GetMapping("/fourProduct")
    public List<Product> getFourProduct() {
        return this.productService.getFourProduct();
    }

    @GetMapping("/allPromotionProduct")
    public PageDTO<Product> getAllPromotionProduct(@RequestParam Integer page, @RequestParam Integer element) {
        return this.productService.getAllPromotionProduct(page, element);
    }

    @GetMapping("/getProductByName")
    public PageDTO<Product> searchProductByName(@RequestParam Integer page, @RequestParam Integer element, @RequestParam String searchValue) {
        return this.productService.getProductByName(page, element, searchValue);
    }

    @GetMapping("/allBestSalesProduct")
    public PageDTO<Product> getAllBestSalesProduct(@RequestParam Integer page, @RequestParam Integer element) {
        return this.productService.getAllBestSalesProduct(page, element);
    }

    @GetMapping("/")
    public PageDTO<Product> getAll(@RequestParam Integer page, @RequestParam Integer element) {
        return this.productService.getAllProduct(page, element);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id) {
        Optional<Product> stock = productService.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Product product) {
        product.setId(id);
        repository.save(product);
//        return repository.findById(id).map(record -> {
//            record.setName(product.getName());
//            record.setPrice(product.getPrice());
//            record.setDescription(product.getDescription());
//            record.setQuantity(product.getQuantity());
//            Product updated = productService.save(record);
//            return ResponseEntity.ok().body(updated);
//        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String name) {
        HttpHeaders headers = new HttpHeaders();

        String path = "D:\\1.Study\\Cloud\\ReactBadminton\\Image\\" + name;
        Path pathFile = Paths.get(path);
        byte[] media = new byte[0];
        try {
            media = Files.readAllBytes(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentType(MediaType.IMAGE_PNG);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProductDTO product) {
        try {
            productService.save(product);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductDTO product) {
        try {
            productService.save(product);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

