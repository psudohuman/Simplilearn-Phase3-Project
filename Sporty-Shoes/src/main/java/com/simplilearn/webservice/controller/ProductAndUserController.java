package com.simplilearn.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.entity.User;
import com.simplilearn.webservice.exceptions.ProductNotFoundException;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ProductAndUserController {
	HttpSession session;
	@Autowired
	ProductRepository pRepo;
	@Autowired
	UserRepository uRepo;
	int f=1;
	
	@GetMapping("/products")
	public List<Product> getProducts(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null) {
			List<Product> list = pRepo.findAll();
			if(list!=null) {
				return list;
			}
			else {
			throw new ProductNotFoundException("Product list empty");
			}
		}
		else {
			return null;
		}
	
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") long id,HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null)
		{
		Product fetchedProduct = pRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException("Product with this id is nt there ");
		}); 
		return fetchedProduct;	
		}
		else
		{
			return null;
		}
	}
	
	// create product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product,HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null)
		{
		return pRepo.save(product);
		}
		else
		{
			return null;
		}
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product,HttpServletRequest request)
	{
		// find a product record
		session = request.getSession(false);
		if(session!=null)
		{
		Product p = pRepo.findById(product.getId()).get();
		if(p!=null) {
			p.setName(product.getName());
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			
			return pRepo.save(p);
		}
		}
		else {
			return null;
		}
		return null;
		}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable("id") long id,HttpServletRequest request){
		
		// find a product record
		session = request.getSession(false);
		if(session!=null)
		{
		Product p = pRepo.findById(id).get();
		if(p!=null) {
			pRepo.delete(p);
			return "Product deleted";
		}
		}else
		{
		return "Product not deleted";
	}
		return null;
	}
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) 
	{	
		String a=user.getName();
		System.out.println(a);
		List<User> users = uRepo.findAll();
		for(User u:users)
		{
			if(u.getName().equals(a))
			{
				f=0;
			}
			else {
					f=1;
				}
			}
		
	if(f==0)
	{
		return null;
	}
	else
	{
	return uRepo.save(user);
	}
			
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") long id,HttpServletRequest request){
		
		// find a product record
		session = request.getSession(false);
		if(session!=null)
		{
		User u = uRepo.findById(id).get();
		if(u!=null) {
			uRepo.delete(u);
			return "User is deleted";
		}
		}else
		{
		return "User not deleted";
	}
		return "User not deleted";
	}
	@GetMapping("/users")
	public List<User> getUsers(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null) {
			List<User> list1 = uRepo.findAll();
			if(list1!=null) {
				return list1;
			}
			else {
			throw new ProductNotFoundException("User list is empty");
			}
		}
		else {
			return null;
		}
	}
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user,HttpServletRequest request)
	{
		// find a product record
		session = request.getSession(false);
		if(session!=null)
		{
		User u = uRepo.findById(user.getId()).get();
		if(u!=null) {
			u.setName(user.getName());
			u.setPass(user.getPass());
			
			
			return uRepo.save(u);
		}
		}
		else {
			return null;
		}
		return null;
		}
}
