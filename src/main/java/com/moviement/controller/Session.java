package com.moviement.controller;

import com.moviement.dto.Board;
import com.moviement.dto.Member;

public class Session {
	private Member loginedMember;
	private Board currentBoard;

	public Member getLoginedMember() {
		return loginedMember;
	}

	public void setLoginedMember(Member loginedMember) {
		this.loginedMember = loginedMember;
	}

	public void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}

	public Board getCurrentBoard() {
		return currentBoard;
	}

	public boolean isLogined() {
		return loginedMember != null;
	}
}