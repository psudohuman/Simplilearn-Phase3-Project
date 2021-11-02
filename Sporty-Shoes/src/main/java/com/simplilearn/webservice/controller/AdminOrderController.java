package com.simplilearn.webservice.controller;

import java.util.Date;
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
import com.simplilearn.webservice.entity.UserPurchase;
import com.simplilearn.webservice.exceptions.ProductNotFoundException;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;
import com.simplilearn.webservice.repository.UserpRepository;


	
	@RestController
	@RequestMapping("/api")
	public class AdminOrderController {
		HttpSession session;
		@Autowired
		ProductRepository pRepo;
		@Autowired
		UserRepository uRepo;
		@Autowired
		UserpRepository upRepo;
		
		
		@GetMapping("/orders")
		public List<UserPurchase> getOrders(HttpServletRequest request){
			session = request.getSession(false);
			if(session!=null) {
				List<UserPurchase> list = upRepo.findAll();
				if(list!=null) {
					return list;
				}
				else {
				throw new ProductNotFoundException("order list empty");
				}
			}
			else {
				return null;
			}
		
	}
		
		@GetMapping("/orders/{id}")
		public UserPurchase getOrder(@PathVariable("id") long id,HttpServletRequest request){
			session = request.getSession(false);
			if(session!=null)
			{
			UserPurchase up = upRepo.findById(id).orElseThrow(()->{
				throw new ProductNotFoundException("Product with the id is nt there !");
			}); 
			return up;	
			}
			else
			{
				return null;
			}
		}
		
	
		@PutMapping("/orders")
		public UserPurchase updateOrder(@RequestBody UserPurchase up,HttpServletRequest request)
		{
			// find a product record
			session = request.getSession(false);
			if(session!=null)
			{
			UserPurchase upp = upRepo.findById(up.getId()).get();
			if(upp!=null) {
		        upp.setBill(up.getBill());
				upp.setId(up.getId());
				upp.setCreatedAt(new Date());
			
				
				return upRepo.save(upp);
			}
			}
			else {
				return null;
			}
			return null;
			}
		
		@DeleteMapping("/orders/{id}")
		public String deleteOrder(@PathVariable("id") long id,HttpServletRequest request){
			
			// find a product record
			session = request.getSession(false);
			if(session!=null)
			{
				
			UserPurchase up = upRepo.findById(id).get();
			if(up!=null) {
				upRepo.delete(up);
				System.out.println("deleted"+id);
				return "Order deleted";
			}
			else
			{
			return null;
		}
			}
			return null;
		}

}