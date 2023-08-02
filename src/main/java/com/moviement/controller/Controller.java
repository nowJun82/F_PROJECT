package com.moviement.controller;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.dto.MovieSeat;

public abstract class Controller {
	public abstract void doAction(int selectNum);

	public void setGrade() {
		Member loginedMember = Container.getSession().getLoginedMember();
		List<MovieSeat> getForPrintSeat = Container.seatService.getForPrintSeats(loginedMember.nickName);

		int num = getForPrintSeat.size();
		if (num >= 8) {
			loginedMember.grade = "VIP";
		} else if (num >= 5 && num < 8) { // 5회 이상시 "gold"
			loginedMember.grade = "gold";
		} else if (num >= 1 && num < 5) { // 1회 이상 4회 미만시 "gold"
			loginedMember.grade = "silver";
		} else { // 초기 회원가입 시 부여되는 등급
			loginedMember.grade = "bronze";
		}
	}

	public float setDc() {
		Member loginedMember = Container.getSession().getLoginedMember();
		if (loginedMember.grade.equals("silver")) {
			return 0.05f;
		} else if (loginedMember.grade.equals("gold")) {
			return 0.1f;
		} else if (loginedMember.grade.equals("VIP")) {
			return 0.2f;
		}
		return 0;
	}

}