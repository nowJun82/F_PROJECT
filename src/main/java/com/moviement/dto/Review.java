package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Review extends Dto {
	public String title;
	public String body;
	public String name;
	public int boardId;
	public float grades;

	public Review (String title, String body, String name, int boardId, float grades) {
		this.title = title;
		this.body = body;
		this.name = name;
		this.boardId = boardId;
		this.grades = grades;
	}
	
	public Review(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.name = (String) row.get("name");
		this.boardId = (int) row.get("boardId");
		this.grades = (float) row.get("grades");
	}
}