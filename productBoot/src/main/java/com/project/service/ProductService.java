package com.project.service;

import java.util.List;

import com.project.model.ProductDto;
import com.project.model.ProductResponse;

public interface ProductService {

	List<ProductResponse> addAllProducts(List<ProductDto> dto);

	List<ProductResponse> getAllProducts();

	ProductResponse getById(Long id);

	ProductResponse updateProduct(Long id, ProductDto dto);

	void deleteProduct(Long id);

}
