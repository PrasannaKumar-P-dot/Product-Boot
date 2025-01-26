package com.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.ProductNotFoundException;
import com.project.model.Product;
import com.project.model.ProductDto;
import com.project.model.ProductResponse;
import com.project.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository repo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<ProductResponse> addAllProducts(List<ProductDto> dto) {
		List<Product> prod = dto.stream().
				map(dto1 -> mapper.map(dto1, Product.class)).
				collect(Collectors.toList());
		repo.saveAll(prod);
		List<ProductResponse> response = prod.stream().
				map(prod1 -> mapper.map(prod1, ProductResponse.class)).
				collect(Collectors.toList());
		return response;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> prod = repo.findAll();
		List<ProductResponse> response = prod.stream().
				map(prod1 -> mapper.map(prod1, ProductResponse.class)).
				collect(Collectors.toList());
		return response;
	}

	@Override
	public ProductResponse getById(Long id) {
		Optional<Product> prod = repo.findById(id);
		
		if(prod.isPresent()) {
			ProductResponse response = mapper.map(prod, ProductResponse.class);
			return response;
		}else {
			throw new ProductNotFoundException("Product not found with id: "+id);
		}
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductDto dto) {
		Optional<Product> prod = repo.findById(id);
		
		if(prod.isPresent()) {
			Product prod1 = prod.get();
			mapper.map(dto, prod1);
			Product pp = repo.save(prod1);
			ProductResponse response = mapper.map(pp, ProductResponse.class);
			return response;
		}else {
			throw new ProductNotFoundException("Produt not found with id:"+id);
		}
	}

	@Override
	public void deleteProduct(Long id) {
		repo.deleteById(id);
	}

}
