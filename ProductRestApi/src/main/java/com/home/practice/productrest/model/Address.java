package com.home.practice.productrest.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Address {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private @NonNull String street;
	private @NonNull String city;
	private  int pincode;
	
	
	@OneToOne
	@JoinColumn(name="dealer_id") //Foreign Key column
	private Dealer dealer;
}
