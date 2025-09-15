package com.app.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Product;
import com.app.ecommerce.service.ProductService;

@RestController
@RequestMapping("ecommerce/product")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws Exception{
		return productService.createProduct(productDto);
	}
	
	@PatchMapping("/{productCode}/update")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("productCode") String productCode,
			@RequestBody Map<String,Object> updates){
		return productService.updateProduct(productCode,updates);
	}
	
	@PostMapping("/{productCode}/delete")
	public ResponseEntity<String> deleteProduct(@PathVariable("productCode") String productCode){
		return productService.deleteProduct(productCode);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productCode}")
	public ResponseEntity<ProductDto> getProductByProductCode(@PathVariable("productCode") String productCode){
		return productService.getProductByProductCode(productCode);
	}
}
