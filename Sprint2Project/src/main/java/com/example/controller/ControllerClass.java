package com.example.controller;


import java.util.List;

import com.example.exceptions.IdNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entiity.Product;
import com.example.entiity.WhishlistProduct;
import com.example.service.ServiceClass;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:4200")

public class ControllerClass {

	@Autowired
	ServiceClass serviceobj;

	// Create Employee
	@PostMapping("/ProductCreation")
	public ResponseEntity<String> ProductCreation(@RequestBody Product emp) {
		Product e = serviceobj.ProductCreation(emp);
		if (e == null) {
			throw new IdNotFoundException("Enter Valid Id");
		} else {
			return new ResponseEntity<String>("Product created successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	//add to wishlist
	@GetMapping("/Whishlist")
	public ResponseEntity<String> addWhishlist(@PathVariable ("id") int id) {
		WhishlistProduct w = serviceobj.addWhishlist(id);
		if (w == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String> ("ITEM WHISHLISTED SUCCESSFULLY", new HttpHeaders(), HttpStatus.OK);
		}
	}
	// Get Particular Employee Data
	@GetMapping("/GetProduct/{id}")
	private ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		Product e = serviceobj.getProductById(id);
		if (e == null) {
			throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
		} else {
			return new ResponseEntity<Product>(e, new HttpHeaders(), HttpStatus.OK);
		}
	}

	// Get All Employees Data
	@GetMapping("/GetAllProducts")
	private ResponseEntity<List<Product>> getAllProducts() {
		List<Product> emplist = serviceobj.getAllProducts();
		return new ResponseEntity<List<Product>>(emplist, new HttpHeaders(), HttpStatus.OK);

	}

	// Updating Employee data
	@PutMapping("/UpdateProduct")
	public ResponseEntity<String> UpdateProduct(@RequestBody Product emp) {
		Product e = serviceobj.UpdateProduct(emp);
		if (e == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Product updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	// Deleting Employee
	@DeleteMapping("/DeleteProduct/{id}")
	private ResponseEntity<String> delEmp(@PathVariable("id") int id) {
		Product e = serviceobj.delete(id);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Employee deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
