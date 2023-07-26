package com.moviement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.moviement.dto.Seat;
import com.moviement.service.SeatService;

public class SeatController extends Controller {
	private Scanner sc;
	private int selectNum;
	private SeatService seatService;
	private Session session;

	public SeatController(Scanner sc) {
		this.sc = sc;
		seatService = new SeatService();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		switch (selectNum) {
		case 1:
			subTicketing();
			break;
//		case 2:
//
		}
	}

	private void subTicketing() {
		
	}
}
