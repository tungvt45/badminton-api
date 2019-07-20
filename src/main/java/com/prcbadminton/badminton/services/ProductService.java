package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.dto.PageDTO;
import com.prcbadminton.badminton.dto.ProductDTO;
import com.prcbadminton.badminton.entities.Image;
import com.prcbadminton.badminton.entities.Producer;
import com.prcbadminton.badminton.entities.Product;
import com.prcbadminton.badminton.entities.Promotion;
import com.prcbadminton.badminton.repository.ImageRepository;
import com.prcbadminton.badminton.repository.ProducerRepository;
import com.prcbadminton.badminton.repository.ProductRepository;
import com.prcbadminton.badminton.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ImageRepository imageRepository;

    public PageDTO<Product> getProductByName(Integer page, Integer element, String searchValue) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, element);
        Page<Product> listData = productRepository.getProductByName(pageable, searchValue);
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    public PageDTO<Product> getAllProduct(Integer page, Integer element) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, element);
        Page<Product> listData = this.productRepository.findAll(pageable);
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    @Override
    public List<Product> getProductInHomePage() {
        return productRepository.getProductInHomePage();
    }

    @Override
    public List<Product> getBestSalesProduct() {
        return productRepository.getBestSalesProduct();
    }

    @Override
    public List<Product> getFourProduct() {
        return productRepository.getFourProduct();
    }

    public PageDTO<Product> getAllPromotionProduct(Integer page, Integer element) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, element);
        Page<Product> listData = productRepository.getAllPromotionProduct(pageable);
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    public PageDTO<Product> getAllBestSalesProduct(Integer page, Integer element) {
        Pageable pageable = PageRequest.of(page - 1, element);
        Page<Product> listData = productRepository.getAllPromotionProduct(pageable);
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    public Optional<Product> findById(int id) {
        return this.productRepository.findById(id);
    }

    public void save(ProductDTO productDTO) throws Exception {
        Product product = new Product();
        if (productDTO.getId() > 0) {
            product.setId(productDTO.getId());
        }
        product.setName(productDTO.getName());
        product.setColor(productDTO.getColor());
        product.setFlex(productDTO.getFlex());
        product.setPrice(productDTO.getPrice());
        product.setShaft(productDTO.getShaft());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());

        Optional<Promotion> promotion = promotionRepository.findById(productDTO.getPromotionId());
        if (promotion.isPresent()) {
            product.setPromotion(promotion.get());
        }
        Optional<Producer> producer = producerRepository.findById(productDTO.getProducerId());
        if (producer.isPresent()) {
           product.setProducer(producer.get());
        }
        Product newProduct = productRepository.save(product);
        List<Image> images = imageRepository.findAllByProduct(newProduct);
        imageRepository.deleteAll(images);
        for (int i = 0; i < productDTO.getImage().size(); i++) {
            Image img = new Image();
            img.setUrl(productDTO.getImage().get(i).getUrl());
            img.setProduct(newProduct);
            imageRepository.save(img);
        }
    }

    public void deleteById(int id) {
        this.productRepository.deleteById(id);
    }


}
