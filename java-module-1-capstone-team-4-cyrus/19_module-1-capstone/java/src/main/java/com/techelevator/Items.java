package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Items {
	
	String name = "";
	String type = "";
	
	
	public Items(String name, String type) {
		
		this.name = name;
		
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
@Override
	public String toString() {
	return "\n" + name + " " + "(" + type + ")";
}
	
}
