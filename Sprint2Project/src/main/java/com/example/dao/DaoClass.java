package com.example.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entiity.Product;
import com.example.entiity.WhishlistProduct;
@Repository
public class DaoClass implements DaoInterface {
	
	
    private SessionFactory sessionFactory;

	@PersistenceContext	
	 EntityManager em;
	
	@Override
	public Product ProductCreation(Product emp) {
		
		Product p=em.merge(emp);
		return p;
	}
	
	@Override
	public Product getProductById(int id) {
		
		return em.find(Product.class,id);
	}
	
	@Override
	public List<Product> getAllProducts() {
		Query q=em.createQuery("select m from Product m");
		List<Product> emplist=q.getResultList();
		return emplist;
	}
	
	
 
	@Override
	public Product UpdateProduct(Product emp) {
		Product e=em.find(Product.class,emp.getId());
		if(e!=null)
		{
			e.setName(emp.getName());
			e.setId(emp.getId());
			e.setPrice(emp.getPrice());
			e.setCategory(emp.getCategory());
		}
		return e;
			
	}
	@Override	
	public Product deleteById(int id) {
		Product e=em.find(Product.class,id);
		if(e!=null)
			{em.remove(e);
			}
        return e;
	}
	
@Override
	public WhishlistProduct addwhishlist(int id)
	{
       Product e=em.find(Product.class, id);
	WhishlistProduct w = null;
	if(e!=null)
	{
		w.setId(e.getId());
		w.setCategory(e.getCategory());
		w.setName(e.getName());
		w.setPrice(e.getPrice());
	}
	return w;
	
	}
}
