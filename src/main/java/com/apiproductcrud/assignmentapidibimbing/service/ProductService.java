package com.apiproductcrud.assignmentapidibimbing.service;

import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductRequest;
import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    Page<ProductResponse> getAllProductsPage(Pageable pageable);

    void deleteProduct(Long id);

    List<ProductResponse> searchProducts(String search);

    List<ProductResponse> findProductsByCategory(String category);

    List<ProductResponse> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductResponse> findAvailableProducts();

    ProductResponse updateAvailableProductStock(Long id, Integer quanity);


}
