package com.app.controller;

import com.app.entity.Product;
import com.app.service.ProductService;

import constant.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping(Api.AUTH)
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/api/product/getProducts")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/api/product/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		String message = productService.addProduct(product);
		return ResponseEntity.ok(message);
	}

	@GetMapping("/api/product/getproduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		try {
			Product product = productService.getProductById(id);
			return ResponseEntity.ok(product);
		} catch (Exception e) {
			// Handle exception appropriately (e.g., log, return specific error message)
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/api/product/deleteProduct/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		return productService.deleteProductById(id);
	}

	@PutMapping("/api/product/editProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		try {
			product.setProductId(id);
			Product updatedProduct = productService.updateProduct(product);
			return ResponseEntity.ok(updatedProduct);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
