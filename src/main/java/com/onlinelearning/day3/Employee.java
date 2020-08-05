package com.onlinelearning.day3;

public class Employee {

	String name;
	int salary;
	Address address;
	
	
	// setter injection requires default constructor
	 public Employee() { }
	 

	public Employee(String name, int salary, Address address) {
		this.name = name;
		this.salary = salary;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Employee getEmployeeInfo() {
		return this;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", address=" + address + "]";
	}
	
}
