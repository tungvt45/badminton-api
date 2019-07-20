package com.prcbadminton.badminton.services;
import com.prcbadminton.badminton.entities.Producer;
import com.prcbadminton.badminton.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prcbadminton.badminton.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@Service
public class ProducerService {
    @Autowired
    private ProducerRepository producerRepository;
    public PageDTO<Producer> getAllProducer(Integer page, Integer element) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, element);
        Page<Producer> listData = this.producerRepository.findAll(pageable);
        PageDTO<Producer> pageDTO = new PageDTO<>();
        pageDTO.setMaxPage(listData.getTotalPages());
        pageDTO.setData(listData.getContent());
        return pageDTO;
    }

    public void save(Producer producer) throws Exception {
        producerRepository.save(producer);
    }
}
