package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Review;
import com.moviement.service.ReviewService;

public class ReviewController extends Controller {
	private Scanner sc;
	private int selectNum;
	private ReviewService reviewService;
	
	public ReviewController(Scanner isc) {
		this.sc = isc;
		reviewService = Container.reviewService;
	}
	
	public void doAction (int selectNum) {
		this.selectNum = selectNum;
		
		if (selectNum == 4) {
			showCommentList();
		}
	}
	
	private void showCommentList() {
		List<Review> forPrintReviews = reviewService.getReviews();
		Review review;
//		
//		while(true) {
			System.out.printf("=== === === R E V I E W === === ===\n\n");
			System.out.println(" 번호 | 닉네임 | 평점 | 제목 ");
			for (int i = 0; i <= forPrintReviews.size()-1; i++) {
				review = forPrintReviews.get(i);
				
				System.out.printf("%4d|%6s|%4.1f|%s\n", review.id, review.name, review.grades, review.title);
			}
			System.out.println();
//			break;
//		}
	}
}