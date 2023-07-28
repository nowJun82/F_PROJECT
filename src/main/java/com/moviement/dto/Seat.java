package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Seat extends Dto {
	public String movieTitle;
	public String seatNum;
	public String nickName;

	public Seat(String movieTitle, String seatNum, String nickName) {
		this.movieTitle = movieTitle;
		this.seatNum = seatNum;
		this.nickName = nickName;
	}

	public Seat(Map<String, Object> row) {
		super(row);
		this.movieTitle = (String) row.get("movieTitle");
		this.seatNum = (String) row.get("seatNum");
		this.nickName = (String) row.get("nickName");
	}
}