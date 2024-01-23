package com.home.practice.productrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealerAndAddressProjection {
	
	private int id;
	private String fname;
	private String lname;
	private String phoneNo;
	private String email;
	private String street;
	private  String city;
	private  int pincode;

}
