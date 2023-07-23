package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Review extends Dto {
	public String title;
	public String body;
	public String name;
	public String memberId;
	public int boardId;
	public float grades;

	public Review (String title, String body, String name, String memberId, int boardId, float grades) {
		this.title = title;
		this.body = body;
		this.name = name;
		this.memberId = memberId;
		this.boardId = boardId;
		this.grades = grades;
	}
	
	public Review(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.name = (String) row.get("name");
		this.memberId = (String) row.get("memberId");
		this.boardId = (int) row.get("boardId");
		this.grades = (float) row.get("grades");
	}
}