package com.apiproductcrud.assignmentapidibimbing.service;

import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductRequest;
import com.apiproductcrud.assignmentapidibimbing.DTOs.ProductResponse;
import com.apiproductcrud.assignmentapidibimbing.entity.Product;
import com.apiproductcrud.assignmentapidibimbing.exception.ProductNotFoundException;
import com.apiproductcrud.assignmentapidibimbing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating new Product: {}", request.getName());

        if (productRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("Product with name " + request.getName()
            + " already exists");
        }

        Product product = mapToEntity(request);

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with id: {}", savedProduct.getId());

        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Updating Product with id: {}", id);

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));


        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStockQuantity(request.getStockQuantity());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setIsActive(request.getIsActive());

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated successfully with id: {}", id);

        return mapToResponse(updatedProduct);
    }


    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Getting product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getAllProductsPage(Pageable pageable) {
        log.info("Fetching all products with pagination {}", pageable);

        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);

        log.info("Product deleted successfully with id: {}", id);
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        log.info("Searching for products with keyword: {}", keyword);

        return productRepository.searchByKeyword(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findProductsByCategory(String category) {
        log.info("Finding products by category: {}", category);

        return productRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        log.info("Finding products by price range: {} and {}", minPrice, maxPrice);

        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAvailableProducts() {
        log.info("Finding available products (active and in stock)");

        return productRepository.findByIsActiveTrueAndStockQuantityGreaterThan(0)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateAvailableProductStock(Long id, Integer quantity) {
        log.info("Updating available product stock with id: {} to quantity: {}", id, quantity);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (quantity < 0){
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        product.setStockQuantity(quantity);
        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());
        product.setIsActive(request.getIsActive());
        return product;
    }


    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .isActive(product.getIsActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
