package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Seat extends Dto {
	public boolean seatEnabled;
	public String movieArticle;
	public String seatNum;
	public String nickName;

	public Seat(boolean seatEnabled, String movieArticle, String seatNum, String nickName) {
		this.seatEnabled = seatEnabled;
		this.movieArticle = movieArticle;
		this.seatNum = seatNum;
		this.nickName = nickName;
	}

	public Seat(Map<String, Object> row) {
		super(row);
		this.seatEnabled = (boolean) row.get("seatEnabled");
		this.movieArticle = (String) row.get("movieArticle");
		this.seatNum = (String) row.get("seatNum");
		this.nickName = (String) row.get("nickName");
	}
}