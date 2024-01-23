package com.home.practice.productrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.practice.productrest.model.Product;
import com.home.practice.productrest.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo prepo;

    public Product saveProduct(Product p) {
        return prepo.save(p);
    }

    @Transactional(readOnly = true)
    public List<Product> listAll() {
        return prepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getSingleProduct(int id) {
        return prepo.findById(id);
    }

    public void deleteProduct(int id) {
        prepo.deleteById(id);
    }

}
