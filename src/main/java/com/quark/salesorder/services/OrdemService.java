package com.quark.salesorder.services;

import java.util.List;
import java.util.Optional;

import com.quark.salesorder.entities.Order;
import com.quark.salesorder.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemService {
    
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Integer id) {
        Optional<Order> obj = repository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
	}

}
