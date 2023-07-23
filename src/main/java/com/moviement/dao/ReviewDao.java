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
	
	public int doWrite(Review review) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("INSERT INTO review "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("title = '%s', ", review.title));
		sb.append(String.format("boardId = '%d', ", review.boardId));
		sb.append(String.format("`name` = '%s', ", review.name));
		sb.append(String.format("grades = '%.1f', ", review.grades));
	
		return dbConnection.insert(sb.toString());
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
	
	public Review getReview(int id) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM review "));
		sb.append(String.format("WHERE id = '%d' ", id));
		
		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Review(row);
	}
}