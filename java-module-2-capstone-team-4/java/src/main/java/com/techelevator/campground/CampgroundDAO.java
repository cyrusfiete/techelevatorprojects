package com.techelevator.campground;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.park.Park;

public interface CampgroundDAO {

	List<Campground> getParkCampgrounds(String chosenPark);
	
	//returns all campgrounds for a given park
	
	public void printParkCampgrounds(Campground campgroundToPrint);
	
	//displays to the user all campgrounds for the chosen park
	
}
