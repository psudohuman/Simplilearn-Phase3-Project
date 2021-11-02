package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.simplilearn.webservice.entity.Cart;
import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.entity.User;
import com.simplilearn.webservice.entity.UserPurchase;
import com.simplilearn.webservice.exceptions.ProductNotFoundException;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserCartRepository;
import com.simplilearn.webservice.repository.UserRepository;
import com.simplilearn.webservice.repository.UserpRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	HttpSession session1;

	@Autowired
	UserpRepository upRepo;
	@Autowired
	UserRepository uRepo;
	@Autowired
	ProductRepository pRepo;
	@Autowired
	UserCartRepository ucRepo;
	
	//User user=new User();
	ArrayList<Product> products1=new ArrayList<Product>();
	double amt=0;
	int f=1;
	//UserPurchase userPurchase=new UserPurchase();
	@PostMapping("/")
	public User userRegister(@RequestBody User user) 
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
	@PutMapping("/userupdate")
	public User updateUser(@RequestBody User user,HttpServletRequest request)
	{
		// find a product record
		session1= request.getSession(false);
		if(session1!=null)
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
	@DeleteMapping("/user/{id}")
	public String userDelete(@PathVariable("id") long id,HttpServletRequest request){
		
		// find a product record
		session1 = request.getSession(false);
		if(session1!=null)
		{
		User u = uRepo.findById(id).get();
		if(u!=null) {
			uRepo.delete(u);
			return "User deleted";
		}
		}else
		{
		return "User not deleted";
	}
		return "User not deleted";
	}
	
	@GetMapping("/products")
	public List<Product> viewProducts(HttpServletRequest request){
		session1 = request.getSession(false);
		if(session1!=null)
		{
		Long i = (Long) session1.getAttribute("id");
		System.out.println(i);
			List<Product> list = pRepo.findAll();
			if(list!=null) {
				return list;
			}
			else {
			throw new ProductNotFoundException("Product list empty");
			}
		}
		else 
		{
			return null;
		}
		}
		
	
	
	@GetMapping("/products/{id}/buy")
	public Cart addtoCart(@PathVariable("id") long id,HttpServletRequest request){
		session1 = request.getSession(false);
		if(session1!=null)
		{
		
		Product fetchedProduct = pRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException("Product not found id");
		});
		System.out.println(fetchedProduct);
		amt+=fetchedProduct.getPrice();
		System.out.println(amt);
		Long i=(Long) session1.getAttribute("id");
		System.out.println(i);
		products1.add(fetchedProduct);
		System.out.println(products1);
		String iid=String.valueOf(i);
		System.out.println(iid);
		//user=uRepo.findById(i);
		UserPurchase up=new UserPurchase();
		Cart c = new Cart();
		c.setBill(amt);
		c.setCreatedAt(new Date());
		c.setProducts(products1);
		 c.setUid(iid);	
        up.setBill(amt);
        up.setUid(iid);	
		up.setProducts(products1);
		up.setCreatedAt(new Date());

		session1.setAttribute("order", up);
			return ucRepo.save(c);
		}
		else
		{
			return null;
		}
	}
	@GetMapping("/products/order")
	public UserPurchase placeOrder(HttpServletRequest request){
		session1 = request.getSession(false);
		
		if(session1!=null)
		{	
			
			UserPurchase upp =(UserPurchase) session1.getAttribute("order");
		return upRepo.save(upp);	
		}
		else
		{
			return null;
		}
		
		
	}
	@GetMapping("/cart/{id}")
	public Cart viewCart(@PathVariable("id") long id,HttpServletRequest request){
		session1 = request.getSession(false);
		if(session1!=null)
		{
		Cart c = ucRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException("Cart dosen't exist with the id");
		}); 
		return c;	
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("/cartremove/{id}")
	public String deleteCart(@PathVariable("id") long id,HttpServletRequest request){
		
		// find a product record
		session1 = request.getSession(false);
		if(session1!=null)
		{
			
		Cart c = ucRepo.findById(id).get();
		if(c!=null) {
			ucRepo.delete(c);
			System.out.println("deleted"+id);
			return "Cart emptied ";
		}
		else
		{
		return null;
	}
		}
		return null;
	}
	@GetMapping("/order/{id}")
	public UserPurchase viewOrder(@PathVariable("id") long id,HttpServletRequest request){
		session1 = request.getSession(false);
		if(session1!=null)
		{
		UserPurchase up = upRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException("no Product exist with the id");
		}); 
		return up;	
		}
		else
		{
			return null;
		}
	}
	
}
