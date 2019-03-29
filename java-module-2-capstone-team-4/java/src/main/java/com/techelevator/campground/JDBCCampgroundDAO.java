package com.techelevator.campground;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.park.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override 
	public List<Campground> getParkCampgrounds(String chosenPark) {
		ArrayList<Campground> parkCampgrounds = new ArrayList <Campground>();
		String sqlTheCampgrounds = "SELECT * FROM campground INNER JOIN park ON park.park_id = campground.park_id WHERE park.name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTheCampgrounds, chosenPark);
		while(results.next()) {
			Campground aCampground = mapRowToCampground(results);
			parkCampgrounds.add(aCampground);
		}
		return parkCampgrounds;
	}
	
	@Override
	public void printParkCampgrounds(Campground campgroundToPrint) {
		System.out.printf("%-35s %-15s %-15s %-15s \n", campgroundToPrint.getName(), campgroundToPrint.getOpen_from_mm(), campgroundToPrint.getOpen_to_mm(), campgroundToPrint.getDaily_fee());
		
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground aCampground = new Campground();
		aCampground.setCampground_id(results.getInt("campground_id"));
		aCampground.setPark_id(results.getInt("park_id"));
		aCampground.setName(results.getString("name"));
		aCampground.setOpen_from_mm(results.getString("open_from_mm"));
		aCampground.setOpen_to_mm(results.getString("open_to_mm"));
		aCampground.setDaily_fee(results.getDouble("daily_fee"));
		return aCampground;
	}
	
}
