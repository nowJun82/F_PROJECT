package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.SeatDao;
import com.moviement.dto.MovieSeat;

public class SeatService {
	private SeatDao seatDao;

	public SeatService() {
		seatDao = Container.seatDao;
	}

	public List<MovieSeat> getForPrintSeats(String nickName) {
		return seatDao.getForPrintSeats(nickName);
	}

	public MovieSeat checkSeat(String movieTitle) {
		return seatDao.checkSeat(movieTitle);
	}

	public MovieSeat getForPrintSeat(String movieTitle, String[] seatStrArr) {
		return seatDao.getForPrintSeat(movieTitle, seatStrArr);
	}

	public MovieSeat getForPrintSeat(int selectNum) {
		return seatDao.getForPrintSeat(selectNum);
	}

	public int doTicketing(String movieTitle, String[] seats, float personPrice) {
		return seatDao.doTicketing(movieTitle, seats, personPrice);
	}

	public MovieSeat getSeat(int id) {
		return seatDao.getSeat(id);
	}

	public List<MovieSeat> getSeats() {
		return seatDao.getSeats();
	}

	public List<MovieSeat> getPrintSeats(String movieTitle) {
		return seatDao.getPrintSeats(movieTitle);
	}

	public void doDeleteSeat(int id) {
		seatDao.doDelete(id);
	}

	public int doWrite() {
		return seatDao.doWrite();
	}
}