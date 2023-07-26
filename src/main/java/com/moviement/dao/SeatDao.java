package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;
import com.moviement.dto.Seat;

public class SeatDao extends Dao {
	private DBConnection dbConnection;

	public SeatDao() {
		dbConnection = Container.getDBConnection();
	}

	public int doTicketing(String movieTitle, String[] titles) {
		Member loginedMember = Container.getSession().getLoginedMember();
		String title;
		int rn = 0;
		
		for (int i = 0; i < titles.length; i++) {
			title = titles[i];
			String sql = String.format("INSERT INTO seats (regDate, updateDate, seatEnabled, movieArticle, seatNum, nickName) VALUES (NOW(), NOW(), %d, '%s', '%s', '%s')", 1, movieTitle, title, loginedMember.nickName);
			int rnn = dbConnection.insert(sql);
			rn += rnn;
		}
		return rn;
	}

	public List<Seat> getForPrintSeats(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM seats "));
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

		sb.append(String.format("SELECT * FROM seats "));

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
		sb.append(String.format("FROM seats "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Seat(row);
	}

	public static List<String> getForPrintSeats() {
		String[] seats = { "x", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14",
				"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "C1", "C2",
				"C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14", "D1", "D2", "D3", "D4",
				"D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14", "E1", "E2", "E3", "E4", "E5", "E6",
				"E7", "E8", "E9", "E10", "E11", "E12", "E13", "E14", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8",
				"F9", "F10", "F11", "F12", "F13", "F14", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
				"G11", "G12", "G13", "G14", "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "H11", "H12",
				"H13", "H14", "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10", "I11", "I12", "I13", "I14",
				"J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10", "J11", "J12", "J13", "J14" };

		List<String> seatsList = new ArrayList<>();
		for (int i = 0; i < seats.length; i++) {
			seatsList.add(seats[i]);
		}
		return seatsList;
	}
}