package com.example.dao;

import java.util.List;

import com.example.entiity.Product;
import com.example.entiity.WhishlistProduct;

public interface DaoInterface {
	Product deleteById(int id);

	List<Product> getAllProducts();

	Product ProductCreation(Product emp);

	Product UpdateProduct(Product emp);

	Product getProductById(int id);

	WhishlistProduct addwhishlist(int id);

}
