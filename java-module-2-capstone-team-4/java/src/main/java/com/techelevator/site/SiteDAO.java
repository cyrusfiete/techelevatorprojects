package com.techelevator.site;

import java.util.List;

import com.techelevator.campground.Campground;

public interface SiteDAO {

	public List<Site> availableSites(int id, String arrival_date, String departure_date);
	
	//returns all sites from the database within the given range of dates
	
	public void printCampgroundSites(Site siteToPrint, double range, double dailyFee);
	
	//displays to the user all available sites in their chosen time frame
	
	public int calculateStayDuration(String arrival, String departure);
	
	//determines how many days long the reservation will be
	
	public void reserveSite(int siteToReserve, String reservationName, String arrivalDate, String departureDate);
	
	//reserves a site and adds the reservation to the database
	
	public int getNextReservationId();
	
	//returns the next id on the reservation list to display to the user as their confirmation number
	
}
