package com.teachalesson.xmlway;

import java.util.Iterator;
import java.util.List;

public class IntegerListCollection {
	
	private int id;
	private List<Integer> integerList;
	
	public IntegerListCollection(int id, List<Integer> integerList) {
		super();
		this.id = id;
		this.integerList = integerList;
	}
	
	public void displayInfo() {
		System.out.println(id );
		System.out.println("Integer Values are:");
		Iterator<Integer> itr = integerList.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
