package com.teachalesson.xmlway.constructordependentinjection;

import com.teachalesson.xmlway.Address;

public class Employee {

	private int id;  
	private String name;  
	private Address address;//Aggregation means HAS-A relation  
	  
	public Employee() {System.out.println("no-arg cons");}  
	  
	public Employee(int id, String name, Address address) {  
	    super();  
	    this.id = id;  
	    this.name = name;  
	    this.address = address;  
	}  
	  
	void show(){  
	    System.out.println("Id : " + id +", Name : "+name);  
	    System.out.println("Address : " + address.toString());  
	}  

}