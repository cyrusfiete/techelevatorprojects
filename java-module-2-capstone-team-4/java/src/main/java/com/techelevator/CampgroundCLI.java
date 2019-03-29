package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.reservation.JDBCReservationDAO;
import com.techelevator.reservation.ReservationDAO;
import com.techelevator.site.JDBCSiteDAO;
import com.techelevator.site.Site;
import com.techelevator.site.SiteDAO;
import com.techelevator.campground.Campground;
import com.techelevator.campground.CampgroundDAO;
import com.techelevator.campground.JDBCCampgroundDAO;
import com.techelevator.park.JDBCParkDAO;
import com.techelevator.park.Park;
import com.techelevator.park.ParkDAO;

public class CampgroundCLI {
	
	private Menu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	private JdbcTemplate jdbcTemplate;
	Scanner keyboard = new Scanner(System.in);
	
	private String[] parkNames;
	static final String MENU_OPTION_QUIT = "Quit Program";

	private static final String PARK_INFO_MENU_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_INFO_MENU_RESERVATION = "Search for Reservation";
	private static final String PARK_INFO_MENU_RETURN = "Return to Previous Screen";
	private static final String[] PARK_INFO_MENU_OPTIONS = new String[] {PARK_INFO_MENU_CAMPGROUNDS,
																		   		PARK_INFO_MENU_RESERVATION,
																		   		PARK_INFO_MENU_RETURN
																		   };
	
	private static final String CAMPGROUNDS_MENU_RESERVATION = "Search for Available Reservations";
	private static final String CAMPGROUNDS_MENU_RETURN = "Return to Previous Screen";
	private static final String[] CAMPGROUNDS_MENU_OPTIONS = new String[] { CAMPGROUNDS_MENU_RESERVATION,
																				   CAMPGROUNDS_MENU_RETURN 
																				   };
	

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		
		this.menu = new Menu(System.in, System.out);
		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
		List<Park> parks = parkDAO.getAllParks();
		parkNames = new String[parks.size()];
		for(int i=0; i<parks.size(); i++) {
			parkNames[i] = (parks.get(i).getName());
		}
	}
	
	private void run() {
		displayApplicationBanner();	
		while(true) {
			//displays parks from database and the user is allowed to choose one
			
			try {
				
			System.out.println("\nSelect a Park for Further Details");
				String choice = (String)menu.getChoiceFromOptions(parkNames);
				for(String option : parkNames) {
				if(choice.equals(option)) {
					handlePark(option);
					}
				}
			}
			catch(Exception e) {
				System.out.println("\nEnter the date in the correct format (yyyy-mm-dd). Please try again.");
			}
		}
	}
	
	//handle methods!
	
	private void handlePark(String chosenPark) {
		Park pickedPark = parkDAO.selectedPark(chosenPark);
		parkDAO.printParkDescription(pickedPark);
		System.out.println("\nSelect a Command");
		String choice = (String)menu.getChoiceFromOptions(PARK_INFO_MENU_OPTIONS);
		if(choice.equals(PARK_INFO_MENU_CAMPGROUNDS)) {
			handleCampgrounds(chosenPark);
		}
		else if(choice.equals(PARK_INFO_MENU_RESERVATION)) {
			//bonus
		}
		else if(choice.equals(PARK_INFO_MENU_RETURN)) {
			//returns to previous menu
		}
	}
	
	private void handleCampgrounds(String chosenPark) {
		List<Campground> parkCampgrounds = new ArrayList<Campground>();
		parkCampgrounds = campgroundDAO.getParkCampgrounds(chosenPark);
		System.out.printf("\n%-35s %-15s %-15s %-15s \n", "Name", "Open", "Close", "Daily Fee");
		for(int i = 0; i < parkCampgrounds.size(); i++) {
			campgroundDAO.printParkCampgrounds(parkCampgrounds.get(i));;
		}
		
		System.out.println("\nSelect a Command\n");
		String choice = (String)menu.getChoiceFromOptions(CAMPGROUNDS_MENU_OPTIONS);
		
		//user chooses a gets to choose a campground
		
		if(choice.equals(CAMPGROUNDS_MENU_RESERVATION)) {
			System.out.printf("\n%-35s %-15s %-15s %-15s \n", "Name", "Open", "Close", "Daily Fee");
			for(int i = 0; i < parkCampgrounds.size(); i++) {
				campgroundDAO.printParkCampgrounds(parkCampgrounds.get(i));;
			}
			
				System.out.println("\nWhich campground? (Enter 0 to cancel)");
				String input = keyboard.nextLine();
				
				if(input.equals("0")) {
					choice.equals(CAMPGROUNDS_MENU_RESERVATION);
				} 
				
				else {
					
				System.out.println("What is the arrival date? yyyy-mm-dd");
				String arrivalInput = keyboard.nextLine();
				
				System.out.println("What is the departure date? yyyy-mm-dd");
				String departureInput = keyboard.nextLine();
				
				
				System.out.println("\nResults Matching Your Search Criteria");
				System.out.printf("\n%-35s %-15s %-15s %-15s %-15s %-15s \n", "Site No.", "Max Occupancy", "Accessible?", "Max RV Length", "Utility", "Cost");
				
				List<Site> listOfSites = new ArrayList<Site>();
				listOfSites = siteDAO.availableSites(Integer.parseInt(input), arrivalInput, departureInput);
				int range = siteDAO.calculateStayDuration(arrivalInput, departureInput);
				for (int i=0; i<listOfSites.size(); i++) {
					siteDAO.printCampgroundSites(listOfSites.get(i), range, parkCampgrounds.get(i).getDaily_fee());
					}
					System.out.println("\nWhich site should be reserved? (Enter 0 to cancel and return to main menu.)");
					String reserveSite = keyboard.next();
					if(reserveSite.equals("0")) {
						run();
					}
					System.out.println("What name should the reservation be made under?");
					String reserveName = keyboard.next();
					//method to reserve
					siteDAO.reserveSite(Integer.parseInt(reserveSite), reserveName, arrivalInput, departureInput);
				}
		}
	}
	
	//display banner
	
	private void displayApplicationBanner() {
		System.out.println(" ______                _        ");
		System.out.println("|  __  |              | |  __   ");
		System.out.println("| |__| | ______  _ __ | | / /   ");
		System.out.println("|  ____||  _   || `__|| |/ /    ");
		System.out.println("| |     | |_|  || |   |  _ \\   ");
		System.out.println("|_|     |____|_||_|   |_| \\_\\ ");
		System.out.println("                                ");
		System.out.println("                                ");
	}
}





