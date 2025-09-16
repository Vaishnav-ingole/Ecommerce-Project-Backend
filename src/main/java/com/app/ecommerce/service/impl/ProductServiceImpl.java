package com.app.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Product;
import com.app.ecommerce.repository.ProductRepository;
import com.app.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private ModelMapper modelMapper;
	private ProductRepository productRepository;

	public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
		this.modelMapper = modelMapper;
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<ProductDto> createProduct(ProductDto productDto) throws Exception {
		ResponseEntity<ProductDto> response = null;
		if(productRepository.existsByProductCode(productDto.getProductCode())) {
			throw new Exception("product already Exists with productCode:"+ productDto.getProductCode());
		}
		try {
			Product productToSave = modelMapper.map(productDto, Product.class);
			Product savedproduct = productRepository.save(productToSave);
			LOG.info("Product succesfully created with code: "+savedproduct.getProductCode());
			response = new ResponseEntity<ProductDto>(modelMapper.map(savedproduct, ProductDto.class),HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("something went wrong while creating product with code:"+productDto.getProductCode());
			response = new ResponseEntity<ProductDto>(productDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ProductDto> updateProduct(String productCode, Map<String, Object> updates) {
		Product product = productRepository.findByProductCode(productCode).orElseThrow();
		
		updates.forEach((key,value) -> {
			Field field = ReflectionUtils.findField(Product.class, key);
			if(Objects.nonNull(field)) {
				field.setAccessible(Boolean.TRUE);
				ReflectionUtils.setField(field, product, value);
			}
			else {
				throw new IllegalArgumentException("Unkown field: "+key);
			}
		});
		if(updates.containsKey("isActive")) {
			boolean isActive = (boolean) updates.get("isActive");
			if(!isActive) {
				product.setDiscountPercentage(0);
				product.setEligibleForDiscount(Boolean.FALSE);
			}
		}
		productRepository.save(product);
		return new ResponseEntity<ProductDto>(modelMapper.map(product, ProductDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteProduct(String productCode) {
		Product product = productRepository.findByProductCode(productCode).orElseThrow();
		product.setActive(Boolean.FALSE);
		product.setEligibleForDiscount(Boolean.FALSE);
		product.setDiscountPercentage(0);
		productRepository.save(product);
		return new ResponseEntity<String>("Product Deleted",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> result = productRepository.findAll().stream().filter(
				product -> product.isActive()).map(
				product -> modelMapper.map(product, ProductDto.class)
				).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDto>>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductDto> getProductByProductCode(String productCode) {
		Product product = productRepository.findByProductCode(productCode).orElseThrow();
		return new ResponseEntity<ProductDto>(modelMapper.map(product, ProductDto.class),HttpStatus.OK);
	}
	
}
