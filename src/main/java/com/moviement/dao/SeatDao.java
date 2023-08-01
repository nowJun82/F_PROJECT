package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;
import com.moviement.dto.MovieArticle;
import com.moviement.dto.MovieSeat;

public class SeatDao extends Dao {
	private DBConnection dbConnection;

	public SeatDao() {
		dbConnection = Container.getDBConnection();
	}

	public int doTicketing(String movieTitle, String[] seats) {
		Member loginedMember = Container.getSession().getLoginedMember();

		int id;
		int rn = 0;
		String seat;
		for (int i = 0; i < seats.length; i++) {
			seat = seats[i];

			StringBuilder sb = new StringBuilder();
			sb.append(String.format("INSERT INTO movieSeats "));
			sb.append(String.format("SET regDate = NOW(), "));
			sb.append(String.format("updateDate = NOW(), "));
			sb.append(String.format("seat = '%s', ", seat));
			sb.append(String.format("movieTitle = '%s', ", movieTitle));
			sb.append(String.format("nickName = '%s', ", loginedMember.nickName));
			sb.append(String.format("enabledSeat = %d; ", 1));
			
			dbConnection.insert(sb.toString());
		}
		return rn;
	}

	public List<MovieSeat> getForPrintSeats(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM movieSeats "));
		sb.append(String.format("WHERE nickName = '%s' ", nickName));

		List<MovieSeat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new MovieSeat(row));
		}
		return seats;
	}

	public List<MovieSeat> getSeats() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM movieSeats "));

		List<MovieSeat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new MovieSeat(row));
		}
		return seats;
	}

	public MovieSeat getSeat(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM movieSeats "));
		sb.append(String.format("WHERE id = %d; ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new MovieSeat(row);
	}

	public List<MovieSeat> getPrintSeats(String movieTitle) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM movieSeats "));
		sb.append(String.format("WHERE movieTitle = '%s' ", movieTitle));

		List<MovieSeat> movieSeats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			movieSeats.add(new MovieSeat(row));
		}
		return movieSeats;
	}

	public int doDelete(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM movieSeats "));
		sb.append(String.format("WHERE id = %d; ", id));

		return dbConnection.delete(sb.toString());
	}

	public int doWrite() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO movieSeats "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("seatNum = null, "));
		sb.append(String.format("movieTitle = null, "));
		sb.append(String.format("nickName = null, "));
		sb.append(String.format("enabledSeat = %d ", 0));

		return dbConnection.insert(sb.toString());
	}

	public MovieSeat checkSeat(String movieTitle) {

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM movieSeats "));
		sb.append(String.format("WHERE movieTitle = '%s' ", movieTitle));
		sb.append(String.format("AND enabledSeat = '%d' ", 1));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new MovieSeat(row);
	}

	public MovieSeat getForPrintSeat(String movieTitle, String[] seatStrArr) {

		StringBuilder sb = new StringBuilder();
		String selectSeat;

		for (int i = 0; i < seatStrArr.length; i++) {
			selectSeat = seatStrArr[i];
			sb.append(String.format("SELECT * "));
			sb.append(String.format("FROM movieSeats "));
			sb.append(String.format("WHERE movieTitle = '%s' ", movieTitle));
			sb.append(String.format("AND seat = '%s'", selectSeat));

			Map<String, Object> row = dbConnection.selectRow(sb.toString());

			if (row.isEmpty()) {
				return null;
			}
			return new MovieSeat(row);
		}
		return null;
	}
}