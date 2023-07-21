package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.MovieArticle;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;

public class MovieArticleController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
	private MovieArticleService movieArticleService;
	private Session session;

	public MovieArticleController(Scanner isc) {
		this.sc = isc;
		memberService = Container.memberService;
		movieArticleService = Container.movieArticleService;
		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		switch (selectNum) {
		case 2: // 상영 중인 영화 목록 페이지
			showMovieList();
			break;
		case 3: // 영화 예매 페이지
			doTicketing();
			break;
		case 9:
			break;
		default:
			System.out.println("다시 입력해주세요.");
			break;
		}
	}

	public void showMovieList() {
		List<MovieArticle> forPrintMovieArticles = movieArticleService.getMovieArticles();
		MovieArticle movieArticle, recommend;
		double recommendNum = Math.random();
		int recommendIntNum = (int) (recommendNum * forPrintMovieArticles.size());

		System.out.printf("=== === === Movie List === === ===\n\n");
		System.out.println(" 번호 | 제목");
		for (int i = 0; i <= forPrintMovieArticles.size() - 1; i++) {
			movieArticle = forPrintMovieArticles.get(i);

			System.out.printf("%4d| %s\n", movieArticle.id, movieArticle.title);
		}
		recommend = forPrintMovieArticles.get(recommendIntNum);
		System.out.printf("\n어떤 걸 볼 지 고민되신다면 %s(은)는 어떠세요?\n", recommend.title);
		System.out.println();
	}

	private void doTicketing() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
	}
}