package com.ecommerce.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.bookstore.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select * from product s where s.name like %:keyword% or s.category_name like %:keyword%", nativeQuery = true)
	 List<Product> findByKeyword(@Param("keyword") String keyword);
}
