package com.learn.models.repos;

import com.learn.models.entities.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository <Supplier, Long> {
    
}
