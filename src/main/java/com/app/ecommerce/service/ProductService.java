package com.app.ecommerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Product;

public interface ProductService {

	ResponseEntity<ProductDto> createProduct(ProductDto productDto);

	ResponseEntity<ProductDto> updateProduct(String productCode, Map<String, Object> updates);

	ResponseEntity<String> deleteProduct(String productCode);

	ResponseEntity<List<ProductDto>> getAllProducts();

	ResponseEntity<ProductDto> getProductByProductCode(String productCode);

}
