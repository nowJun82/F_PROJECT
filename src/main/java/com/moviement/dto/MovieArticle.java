package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MovieArticle extends Dto {
	public String title;
	public int boardId;
	
	public MovieArticle(int boardId, String title) {
		this.title = title;
		this.boardId = boardId;
	}
	
	public MovieArticle(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.boardId = (int) row.get("boardId");
	}
}