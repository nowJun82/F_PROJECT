package com.moviement.dto;

import java.util.Map;

public class Review extends Dto {
	public String title;
	public String body;
	public int memberId;
	public int boardId;
	public double grades;

	public Review (int memberId, int boardId, String title, String body, double grades) {
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.boardId = boardId;
		this.grades = grades;
	}
	
	public Review(Map<String, Object> row) {
		super(row);
	}
}