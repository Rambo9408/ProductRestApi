package com.home.practice.productrest.controller;

import com.home.practice.productrest.model.Product;
import com.home.practice.productrest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class productController {

    @Autowired
    private ProductService pservice;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello world from Spring Boot");
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product) {
        try {
            Product p = pservice.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = pservice.listAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable(value = "id") int pId) {
        try {
            Optional<Product> p = pservice.getSingleProduct(pId);
            return ResponseEntity.ok().body(p);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int pId,
                                                 @Validated @RequestBody Product p) {
        try {
            Product existingProduct = pservice.getSingleProduct(pId)
                    .orElse(null);

            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Update product with new values
            existingProduct.setBrand(p.getBrand());
            existingProduct.setMadein(p.getMadein());
            existingProduct.setPname(p.getPname());
            existingProduct.setPrice(p.getPrice());

            final Product updatedProduct = pservice.saveProduct(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable(value = "id") int pId) {
        try {
            Product existingProduct = pservice.getSingleProduct(pId)
                    .orElse(null);

            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            pservice.deleteProduct(existingProduct.getPid());

            Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
