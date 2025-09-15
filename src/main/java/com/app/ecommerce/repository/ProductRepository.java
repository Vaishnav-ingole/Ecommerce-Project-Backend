package com.app.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	boolean existsByProductCode(String productCode);

	Optional<Product> findByProductCode(String productCode);

}
