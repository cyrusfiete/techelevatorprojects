package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Slots {
	
	
	String slotName = "";
	double price = 0.0;
	Stack<Items> products;
	
	public Slots(double price, Stack<Items> products) {
		this.price = price;
		this.products = products;
	}
	public double getPrice() {
		return price;
	}
	public String getName() {
		return products.pop().getName();
	}
	
	public String getType() {
		return products.peek().getType();
	}
	
	public String seeName() {
		return products.peek().getName();
	}
	
	public boolean stackEmpty() {
		if (products.empty()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return " " + price + " " + products;
	}
	

}

