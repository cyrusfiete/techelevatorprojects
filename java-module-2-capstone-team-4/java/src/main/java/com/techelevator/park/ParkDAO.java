package com.techelevator.park;

import java.util.List;

public interface ParkDAO {

	public List<Park> getAllParks();
	
	//returns all parks from the database
	
	public Park selectedPark(String chosenPark);
	
	//returns the park given by the user's input
	
	public void printParkDescription(Park parkToPrint);
	
	//displays to the user all information about the chosen park
	
}
