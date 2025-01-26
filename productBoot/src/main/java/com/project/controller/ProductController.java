package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.ProductDto;
import com.project.model.ProductResponse;
import com.project.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PostMapping("/prod")
	public List<ProductResponse> addAllProducts(@RequestBody List<ProductDto> dto){
		return service.addAllProducts(dto);
	}

	@GetMapping("/all")
	public List<ProductResponse> getAllProducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ProductResponse getById(@PathVariable(value = "id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping("/update/{id}")
	public ProductResponse updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDto dto) {
		return service.updateProduct(id,dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) {
		service.deleteProduct(id);
	}
}
