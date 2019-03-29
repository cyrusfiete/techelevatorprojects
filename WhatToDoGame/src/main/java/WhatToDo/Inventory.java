package WhatToDo;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	public static final String CANDLE = "Candle";
	public static final String LADDER = "Ladder";
	public static final String TARP = "Tarp";
	public static final String MATCH = "Match";
	public static final String ASHES = "Ashes";
	public static final String BRANCH = "Branch";
	public static final String MONKEY = "Monkey";
	
	private Map<String, Boolean> inventory = new HashMap<String, Boolean>();
	
	public Inventory() {
		
		inventory.put(CANDLE, true);
		inventory.put(LADDER, false);
		inventory.put(TARP, false);
		inventory.put(MATCH, false);
		inventory.put(ASHES, false);
		inventory.put(BRANCH, false);
		inventory.put(MONKEY, false);
		
	}

	public void addItem(String newItem) {
		inventory.put(newItem, true);
	}
	
	public void usedItem(String loseItem) {
		inventory.put(loseItem, false);
	}
	
	public Boolean hasItem(String hasItem) {
		return inventory.get(hasItem);
	}

}
