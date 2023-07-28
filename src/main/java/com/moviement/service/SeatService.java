package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.SeatDao;
import com.moviement.dto.Review;
import com.moviement.dto.Seat;

public class SeatService {
	private SeatDao seatDao;

	public SeatService() {
		seatDao = Container.seatDao;
	}

	public List<Seat> getForPrintSeats(String nickName) {
		return seatDao.getForPrintSeats(nickName);
	}

	public int doTicketing(String movieTitle, int[] seatId, String[] seatNums) {
		return seatDao.doTicketing(movieTitle, seatId, seatNums);
	}
	
	public Seat getSeat(int id) {
		return seatDao.getSeat(id);
	}

	public List<Seat> getSeats() {
		return seatDao.getSeats();
	}

	public List<Seat> getPrintDefaultSeats() {
		return seatDao.getPrintDefaultSeats();
	}

	public void doDeleteSeat(int id) {
		seatDao.doDelete(id);
	}
	
	public int doWrite() {
		return seatDao.doWrite();
	}
}