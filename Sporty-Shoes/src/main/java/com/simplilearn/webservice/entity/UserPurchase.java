package com.simplilearn.webservice.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_purchase")
public class UserPurchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private long id;
	
	@Column(name="user_id")
	private String uid;
	
	
	@Column(name = "created_At")
	private Date createdAt=new Date();

	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Product> products;
	
	@Column(name="bill")
	private double bill;

	
	public UserPurchase() {
		super();
	}





	public UserPurchase(long id, String uid, Date createdAt, List<Product> products, double bill) {
		super();
		this.id = id;
		this.uid = uid;
		this.createdAt = createdAt;
		this.products = products;
		this.bill = bill;
	}





	public long getId() {
		return id;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public double getBill() {
		return bill;
	}


	public void setBill(double bill) {
		this.bill = bill;
	}


	@Override
	public String toString() {
		return "UserPurchase [id=" + id + ", uid=" + uid + ", createdAt=" + createdAt + ", products=" + products
				+ ", bill=" + bill + "]";
	}


	
	

}
