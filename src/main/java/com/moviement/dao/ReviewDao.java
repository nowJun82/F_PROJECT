package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Review;

public class ReviewDao {
	private DBConnection dbConnection;
	
	public ReviewDao() {
		dbConnection = Container.getDBConnection();
	}
	
	public List<Review> getReviews() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("SELECT * FROM review"));
		List<Review> reviews = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for (Map<String, Object> row : rows) {
			reviews.add(new Review(row));
		}
		return reviews;
	}
}