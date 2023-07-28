package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.dto.MovieArticle;
import com.moviement.dto.Review;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;
import com.moviement.service.ReviewService;

public class ReviewController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
	private MovieArticleService movieArticleService;
	private ReviewService reviewService;
	private Session session;

	public ReviewController(Scanner isc) {
		this.sc = isc;
		memberService = new MemberService();
		movieArticleService = new MovieArticleService();
		reviewService = Container.reviewService;
		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		if (selectNum == 3) {
			showCommentList();
		}
	}

	private void showCommentList() {
		List<Review> forPrintReviews = reviewService.getReviews();
		Review review;

		System.out.printf("=== === === R E V I E W === === ===\n\n");
		System.out.println(" 번호 | 닉네임 | 평점 | 영화 제목 ");
		for (int i = 0; i <= forPrintReviews.size() - 1; i++) {
			review = forPrintReviews.get(i);

			System.out.printf("%3d |%4s |%4.1f | %s\n", review.id, review.name, review.grades, review.title);
		}
		System.out.println();

		System.out.printf("1. 리뷰 작성\n");
		System.out.printf("2. 리뷰 수정\n");
		System.out.printf("3. 리뷰 삭제\n");
		System.out.printf("9. 이전 단계로\n\n");
		System.out.print("선택 : ");
		int selectNum = sc.nextInt();
		System.out.println();

		switch (selectNum) {
		case 1:
			doWrite();
			break;
		case 2:
			doModify();
			break;
		case 3:
			doDelete();
			break;
		case 9:
			break;
		default:
			System.out.println("다시 입력해주세요.\n");
		}
	}

	public void doWrite() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
		Member loginedMember = Container.getSession().getLoginedMember();
		List<MovieArticle> forPrintMovieArticles = movieArticleService.getMovieArticles();
		MovieArticle movieArticle;

		System.out.println(" 번호 | 제목 ");
		for (int i = 0; i <= forPrintMovieArticles.size() - 1; i++) {
			movieArticle = forPrintMovieArticles.get(i);
			System.out.printf("%3d | %s\n", movieArticle.id, movieArticle.title);
		}
		System.out.println();
		System.out.print("리뷰를 작성할 영화의 번호 입력 : ");
		int choiceMovieArticleBoardId = sc.nextInt();

		MovieArticle getMovieArticle = movieArticleService.getMovieArticle(choiceMovieArticleBoardId);
		String reviewTitle = getMovieArticle.title;
		System.out.printf("\n=== === === %s 리뷰 작성 === === ===\n", reviewTitle);
		System.out.printf("평점 : ");
		float grades = sc.nextFloat();
		sc.nextLine();

		System.out.printf("리뷰 내용 : ");
		String reviewBody = sc.nextLine();
		reviewService.write(reviewTitle, reviewBody, loginedMember.nickName, grades);
		System.out.println("\n리뷰가 작성되었습니다. 감사합니다.\n");
	}

	public void doModify() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
		
		int selectNum;
		Member loginedMember = Container.getSession().getLoginedMember();
		List<Review> forPrintReview = Container.reviewService.getForPrintReviews(loginedMember.nickName);

		if (forPrintReview.size() == 0) {
			System.out.println("작성하신 리뷰가 존재하지 않습니다.");
			return;
		}

		System.out.printf("=== === === M Y R E V I E W === === ===\n\n");
		System.out.println(" 번호 | 닉네임 | 평점 | 제목 ");

		Review review;
		for (int i = 0; i <= forPrintReview.size() - 1; i++) {
			if (forPrintReview.size() == 0) {
				System.out.println("작성하신 리뷰가 존재하지 않습니다.\n");
				return;
			}
			review = forPrintReview.get(i);

			System.out.printf(" %2d | %5s | %3.1f | %s | %s \n", review.id, review.name, review.grades, review.title,
					review.body);
		}
		
		while (true) {
			System.out.println("\n수정을 원하는 리뷰의 번호를 입력해주세요.");
			System.out.print("입력 : ");
			selectNum = sc.nextInt();
			sc.nextLine();
			
			Review choiceReview = Container.reviewService.getForPrintReview(selectNum);
			
			System.out.printf("\n현재 내용 : %s\n", choiceReview.body);
			System.out.printf("수정할 내용 입력 : ");
			String body = sc.nextLine();
			System.out.println("\n내용 수정이 완료되었습니다.");
			
			System.out.printf("\n현재 평점 : %.1f\n", choiceReview.grades);
			System.out.printf("수정할 평점 입력 : ");
			float grades = sc.nextFloat();
			
			reviewService.modifyReview(choiceReview.id, body, grades);
			
			System.out.println("\n리뷰 수정이 완료되었습니다.\n");
			break;
		}
	}

	public void doDelete() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}

		Member loginedMember = Container.getSession().getLoginedMember();
		List<Review> forPrintReviews = reviewService.getReviews();
		Review review;

		System.out.println(" 번호 | 제목 | 내용 | 닉네임");
		for (int i = 0; i <= forPrintReviews.size() - 1; i++) {
			review = forPrintReviews.get(i);
			System.out.printf("%3d | %s | %s | %s \n", review.id, review.title, review.body, review.name);
		}
		System.out.println();
		System.out.println("리뷰 선택 : ");
		int choiceReviewId = sc.nextInt();

		Review getReview = reviewService.getReview(choiceReviewId);
		String nickName = getReview.name;

		if (loginedMember.nickName.equals(nickName) == false) {
			System.out.println("권한이 없습니다.");
			return;
		}
		reviewService.delete(choiceReviewId);
		System.out.println("리뷰가 삭제되었습니다.");
	}
}