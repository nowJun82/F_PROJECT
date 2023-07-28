package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;
import com.moviement.dto.Review;
import com.moviement.dto.Seat;

public class SeatDao extends Dao {
	private DBConnection dbConnection;

	public SeatDao() {
		dbConnection = Container.getDBConnection();
	}

	public int doTicketing(String movieTitle, int[] seatId, String[] seatNums) {
		Member loginedMember = Container.getSession().getLoginedMember();
		
		int id;
		int rn = 0;
		String seatNum;
		for (int i = 0; i < seatId.length; i++) {
			seatNum = seatNums[i];
			id = seatId[i];
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("UPDATE defaultSeats "));
			sb.append(String.format("SET updateDate = NOW(), "));
			sb.append(String.format("seatNum = '%s', ", seatNum));
			sb.append(String.format("movieTitle = '%s', ", movieTitle));
			sb.append(String.format("nickName = '%s', ", loginedMember.nickName));
			sb.append(String.format("enabledSeat = %d ", 1));
			sb.append(String.format("WHERE id = %d; ", id));
			dbConnection.update(sb.toString());
		}
		return rn;
	}

	public List<Seat> getForPrintSeats(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM defaultSeats "));
		sb.append(String.format("WHERE nickName = '%s' ", nickName));

		List<Seat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new Seat(row));
		}
		return seats;
	}

	public List<Seat> getSeats() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM defaultSeats "));

		List<Seat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new Seat(row));
		}
		return seats;
	}

	public Seat getSeat(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM defaultSeats "));
		sb.append(String.format("WHERE id = %d; ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Seat(row);
	}

	public List<Seat> getPrintDefaultSeats() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM defaultSeats "));
		List<Seat> defaultSeats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			defaultSeats.add(new Seat(row));
		}
		return defaultSeats;
	}
	
	public int doDelete(int id) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("UPDATE defaultSeats "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("seatNum = null, "));
		sb.append(String.format("movieTitle = null, "));
		sb.append(String.format("nickName = null, "));
		sb.append(String.format("enabledSeat = %d ", 0));
		sb.append(String.format("WHERE id = %d; ", id));
		
		dbConnection.update(sb.toString());
		
		return dbConnection.delete(sb.toString());
	}
	
	public int doWrite() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO defaultSeats "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("seatNum = null, "));
		sb.append(String.format("movieTitle = null, "));
		sb.append(String.format("nickName = null, "));
		sb.append(String.format("enabledSeat = %d ", 0));

		return dbConnection.insert(sb.toString());
	}
}