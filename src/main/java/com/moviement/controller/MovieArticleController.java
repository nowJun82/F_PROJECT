package com.moviement.controller;

import java.util.Scanner;

public class MovieArticleController extends Controller {
	private Scanner sc;
	private int selectNum;
//	private ArticleService articleService;
//	private Session session;
	
	public MovieArticleController(Scanner isc) {
		this.sc = isc;
//		memberService = Container.memberService;
//		session = Container.getSession();
	}
	public void doAction(int selectNum) {
		this.selectNum = selectNum;
		
		switch (selectNum) {
		case 2: // 상영 중인 영화 목록 페이지 : movie article
			showMovieList();
			break;
		case 3: // 리뷰 & 평점 페이지 : board
			showCommentList();
			break;
		case 4: // 영화 추천 페이지 : movie article
			doRecommend();
			break;
		case 5: // 영화 예매 페이지 : movie
			doTicketing();
			break;
		default:
			System.out.println("다시 입력해주세요.");
			break;
		}
	}
	private void showMovieList() {
		
	}
	private void showCommentList() {
		
	}
	private void doRecommend() {
		
	}
	private void doTicketing() {
		
	}
}