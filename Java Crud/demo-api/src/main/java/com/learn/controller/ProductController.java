package com.learn.controller;

import java.util.List;
// import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import com.learn.dto.ResponseData;
import com.learn.dto.SearchData;
import com.learn.models.entities.Product;
import com.learn.models.entities.Supplier;
import com.learn.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    //26.64

    // controller pangiil service, service manggil crud repo
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity < ResponseData < Product >> create (@Valid @RequestBody Product product, Errors  errors){

        ResponseData <Product> responseData = new ResponseData<>();

        String uCode = "ABCDEFGHIJKLMNOPQRSTUWVXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 15) {
            int index = (int) (rnd.nextFloat() * uCode.length());
            salt.append(uCode.charAt(index));
        }
        String code2 = salt.toString();

        product.setCode(code2);

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
              responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable <Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findone(id);
    }

    // @GetMapping("/{name}")
    // public List<Product> findByName(@PathVariable("name") String name) {
    //     return productService.findByName(name);
    // }

    @PutMapping
    public ResponseEntity <ResponseData<Product>> update ( @Valid @RequestBody Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
              responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
        System.out.println("data deleted");
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productService.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List <Product> getProductByNameLike(@RequestBody SearchData searchData){
        return productService.findByProductNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List <Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findByCategory(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List <Product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productService.findBySupplier(supplierId);
    }
}
