package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.SeatDao;
import com.moviement.dto.Member;
import com.moviement.dto.Seat;

public class SeatService {
	private SeatDao seatDao;

	public SeatService() {
		seatDao = Container.seatDao;
	}

	public List<String> getForPrintSeats() {
		return seatDao.getForPrintSeats();
	}
	
	public List<Seat> getForPrintSeats(String nickName) {
		return seatDao.getForPrintSeats(nickName);
	}

	public int doTicketing(String movieTitle, String[] titles) {
		return seatDao.doTicketing(movieTitle, titles);
	}

	public List<Seat> getSeats() {
		return seatDao.getSeats();
	}
}