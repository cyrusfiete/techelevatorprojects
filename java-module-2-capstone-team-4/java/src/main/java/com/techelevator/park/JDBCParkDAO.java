package com.techelevator.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		ArrayList<Park> allParks = new ArrayList <Park>();
		String sqlTheParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTheParks);
		while(results.next()) {
			Park aPark = mapRowToPark(results);
			allParks.add(aPark);
		}
		return allParks;
	}
	
	@Override
	public Park selectedPark(String chosenPark) {
		String pickedPark = "";
		String sqlThePark = "SELECT * " +
								"FROM park " +
								"WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlThePark, chosenPark);
		Park aPark = null;
		if (results.next()) {
			aPark = mapRowToPark(results);
		}
		return aPark;
	}
	
	@Override
	public void printParkDescription(Park parkToPrint) {
		System.out.println("\n" + parkToPrint.getName() + " National Park");
		System.out.printf("%-20s %-15s \n", "Location:", parkToPrint.getLocation());
		System.out.printf("%-20s %-15s \n", "Established:", parkToPrint.getEstablish_date());
		System.out.printf("%-20s %-15s \n", "Area:", parkToPrint.getArea());
		System.out.printf("%-20s %-15s \n", "Annual Visitors:", parkToPrint.getVisitors());
		System.out.println(parkToPrint.getDescription());
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park aPark = new Park();
		aPark.setPark_id(results.getInt("park_id"));
		aPark.setName(results.getString("name"));
		aPark.setLocation(results.getString("location"));
		aPark.setEstablish_date(results.getDate("establish_date").toLocalDate());
		aPark.setArea(results.getInt("area"));
		aPark.setVisitors(results.getInt("visitors"));
		aPark.setDescription(results.getString("description"));
		return aPark;
	}
}
