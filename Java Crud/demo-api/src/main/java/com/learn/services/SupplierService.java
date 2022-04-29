package com.learn.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.learn.models.entities.Supplier;
import com.learn.models.repos.SupplierRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;
    
    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public Iterable <Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

}
