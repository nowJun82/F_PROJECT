package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MovieSeat extends Dto {
	public String seat;
	public String movieTitle;
	public String nickName;
	public float price;
	public boolean enabledSeat;

	public MovieSeat(String seat, String movieTitle, String nickName, float price, boolean enabledSeat) {
		this.seat = seat;
		this.movieTitle = movieTitle;
		this.nickName = nickName;
		this.price = price;
		this.enabledSeat = enabledSeat;
	}

	public MovieSeat(Map<String, Object> row) {
		super(row);
		this.seat = (String) row.get("seat");
		this.movieTitle = (String) row.get("movieTitle");
		this.nickName = (String) row.get("nickName");
		this.price = (float) row.get("price");
		this.enabledSeat = (boolean) row.get("enabledSeat");
	}
}