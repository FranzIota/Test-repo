package com.learn.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import com.learn.models.entities.Product;
import com.learn.models.entities.Supplier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// ini pake repo buat create update delete
public interface ProductRepo extends CrudRepository <Product,Long> {
    
    // ini custom function

    public List<Product> findByNameContaining(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName (@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List <Product> findProductByNameLike (@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List <Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
