// bisnis logic

package com.learn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;

import javax.transaction.Transactional;

import com.learn.models.entities.Product;
import com.learn.models.entities.Supplier;
import com.learn.models.repos.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    // yg .save - .deleteById, itu function yng udah ada dri crud repo
    public Product save (Product product){
        return productRepo.save(product);
    }

    public Product findone(Long id){
        Optional <Product> product = productRepo.findById(id);

        if(!product.isPresent()){
            return null;
        }
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    // ini yg custom function (diketik manual di ProductRepo.java) 
    public List<Product> findByName(String name){
        // List<Product> product = productRepo.findByNameContains(name);

        // if(!product.isEmpty()){
        //     return null;
        // }

        List<Product> resp = productRepo.findByNameContaining(name);


        return resp;
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findone(productId);

        if(product == null){
            throw new RuntimeException("Product with id: "+productId+" not found ");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }
    
    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike( String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier (Long supplierId){
        Supplier supplier =  supplierService.findOne(supplierId);
        if(supplier == null){
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
}
