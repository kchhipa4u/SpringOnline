package com.teachalesson.xmlway.constructorinjection;
public class Employee {

	private int id;
	private String name;
	private String address;

	public Employee() {
		System.out.println("no-arg  cons");
	}

	public Employee(int id) {
		this.id = id;
	}

	public Employee(String name) {
		this.name = name;
	}
	
	/*public Employee( String name,int id) {
		this.id = id;
		this.name = name;
	}*/

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Employee(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	void show() {
		System.out.println(id + " " + name + "  " + address);
	}

}
