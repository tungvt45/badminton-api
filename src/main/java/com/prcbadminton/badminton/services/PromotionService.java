package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.entities.Promotion;
import com.prcbadminton.badminton.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prcbadminton.badminton.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public PageDTO<Promotion> getAllPromotion(Integer page, Integer element) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, element);
        Page<Promotion> listData = this.promotionRepository.findAll(pageable);
        PageDTO<Promotion> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    public void save(Promotion promotion) throws Exception {
        promotionRepository.save(promotion);
    }
}

