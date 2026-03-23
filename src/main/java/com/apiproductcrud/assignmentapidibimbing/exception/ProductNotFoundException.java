package com.apiproductcrud.assignmentapidibimbing.exception;

import com.apiproductcrud.assignmentapidibimbing.entity.Product;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }


    public ProductNotFoundException(Long id){
        super("Product not found with id:  " + id);
    }
}
