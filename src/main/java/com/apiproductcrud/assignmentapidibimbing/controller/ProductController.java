package com.apiproductcrud.assignmentapidibimbing.controller;


import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductRequest;
import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductResponse;
import com.apiproductcrud.assignmentapidibimbing.entity.Product;
import com.apiproductcrud.assignmentapidibimbing.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<ProductResponse> products = productService.getAllProductsPage(pageable);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        List<ProductResponse> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String category){
        List<ProductResponse> products = productService.findProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponse>> getProductsByPriceRange(
            @RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        List<ProductResponse> products = productService.findProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/available")
    public ResponseEntity<List<ProductResponse>> getAvailableProducts(){
        List<ProductResponse> products = productService.findAvailableProducts();
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> updateStock(@PathVariable Long id,
                                                             @RequestParam Integer quantity) {
        ProductResponse response = productService.updateAvailableProductStock(id, quantity);
        return ResponseEntity.ok(response);
    }




}
