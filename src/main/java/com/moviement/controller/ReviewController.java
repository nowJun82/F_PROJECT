package com.moviement.controller;

import java.util.Scanner;

public class ReviewController extends Controller {
	private Scanner sc;
	private int selectNum;
	
	public ReviewController(Scanner isc) {
		this.sc = isc;
	}
	
	public void doAction (int selectNum) {
		this.selectNum = selectNum;
		
		if (selectNum == 4) {
			
		}
	}
}