package com.home.practice.productrest.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {
	//this is used to make id start from 100 and get incremented by 1 each time new product is added. 
	@SequenceGenerator(name="product_seq", initialValue = 100, allocationSize =1)
	
	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_seq")
	private int pid;
	
	private String pname;
	private String brand;
	
	@Column(nullable = false)
	private String madein;
	private float price;
	
	public Product() {}

	public Product(int pid, String pname, String brand, String madein, float price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.brand = brand;
		this.madein = madein;
		this.price = price;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMadein() {
		return madein;
	}

	public void setMadein(String madein) {
		this.madein = madein;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
