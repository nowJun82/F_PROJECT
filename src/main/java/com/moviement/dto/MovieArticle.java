package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MovieArticle extends Dto {
	public String title;
	public String body;
	public int price;
	
	public MovieArticle(String title, String body, int price) {
		this.title = title;
		this.body = body;
	}
	
	public MovieArticle(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.price = (int) row.get("price");
	}
}