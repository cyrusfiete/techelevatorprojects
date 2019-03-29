package com.techelevator.site;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.Campground;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	@Override
	public List<Site> availableSites(int id, String arrival_date, String departure_date) {
		LocalDate arrivalDate = LocalDate.parse(arrival_date);
		LocalDate departureDate = LocalDate.parse(departure_date);
		List<Site> campgroundSites = new ArrayList<Site>();
		String sqlTheSites = "SELECT * FROM site "
						   + "WHERE campground_id = ? "
						   + "AND site_id NOT IN "
						   + "(SELECT site_id FROM reservation "
						   + "WHERE ((to_date BETWEEN ? AND ?) "
						   + "OR (from_date BETWEEN ? AND ?))"
						   + "GROUP BY site_id) LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTheSites, id, arrivalDate, departureDate, arrivalDate, departureDate);
		Site aSite = null;
		while(results.next()) {
			aSite = mapRowToSite(results);
			campgroundSites.add(aSite);
		}
		return campgroundSites;
	}
	
	@Override
	public void printCampgroundSites(Site siteToPrint, double range, double dailyFee) {
		System.out.printf("%-35s %-15s %-15s %-15s %-15s %-15s \n", siteToPrint.getSite_id(), siteToPrint.getMax_occupancy(), siteToPrint.isAccessible(), siteToPrint.getMax_rv_length(), siteToPrint.isUtilities(), range*dailyFee);
		// Want to get the daily fee from campground, and work it into the method. Total cost will equal number of days * nightly cost.
	}
	
	@Override
	public int calculateStayDuration(String arrival, String departure) {
		String sqlTheRange = "SELECT ? - ?";
		LocalDate arrivalDate = LocalDate.parse(arrival);
		LocalDate departureDate = LocalDate.parse(departure);
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTheRange, departureDate, arrivalDate);
		int range = 0;
		if(results.next()) {
			range = results.getInt(1);
		}
		return range;
	}
	
	public void reserveSite(int siteToReserve, String reservationName, String arrivalDate, String departureDate) {
		LocalDate arrival = LocalDate.parse(arrivalDate);
		LocalDate departure = LocalDate.parse(departureDate);
		String sqlReserve = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) "
				   			+ "VALUES (?, ?, ?, ?, current_date)";
		
		int result = jdbcTemplate.update(sqlReserve, siteToReserve, reservationName, arrival, departure);
		if(result == 1) {
		System.out.println("\nThe reservation has been made and the confirmation ID is :" + getNextReservationId());
		}
	}
	
	public int getNextReservationId() {
		
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("ERROR.");
		}
		
	}
	
	private Site mapRowToSite(SqlRowSet results) {
		Site aSite = new Site();
		aSite.setSite_id(results.getInt("site_id"));
		aSite.setCampground_id(results.getInt("campground_id"));
		aSite.setSite_number(results.getInt("site_number"));
		aSite.setMax_occupancy(results.getInt("max_occupancy"));
		aSite.setAccessible(results.getBoolean("accessible"));
		aSite.setMax_rv_length(results.getInt("max_rv_length"));
		aSite.setUtilities(results.getBoolean("utilities"));
		return aSite;
	}

}
