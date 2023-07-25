package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.MovieArticle;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;
import com.moviement.service.SeatService;

public class MovieArticleController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
	private MovieArticleService movieArticleService;
	private SeatService seatService;
	private Session session;

	public MovieArticleController(Scanner isc) {
		this.sc = isc;
		memberService = Container.memberService;
		movieArticleService = Container.movieArticleService;
		seatService = Container.seatService;
		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		switch (selectNum) {
		case 2:
			showMovieList();
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
		MovieArticle movieArticle;

		System.out.print("=== === === Movie List === === ===\n\n");
		System.out.println(" 번호 | 제목");

		for (int i = 0; i <= forPrintMovieArticles.size() - 1; i++) {
			movieArticle = forPrintMovieArticles.get(i);

			System.out.printf("%3d | %s\n", i + 1, movieArticle.title);
		}
		System.out.println();

		while (true) {
			System.out.printf("1. 영화 추천받기\n");
			System.out.printf("2. 영화 예매하기\n");
			System.out.printf("0. 이전 단계로\n\n");
			System.out.printf("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();

			switch (selectNum) {
			case 1:
				doRecommend();
				continue;
			case 2:
				doTicketing();
				break;
			case 0:
				System.out.println("이전 단계로 돌아갑니다.\n");
				break;
			default:
				System.out.println("입력한 번호를 확인 후 다시 입력해주세요.\n");
				continue;
			}
			break;
		}
	}

	private void doRecommend() {
		List<MovieArticle> forPrintGetMovieArticles = movieArticleService.getMovieArticles();
		MovieArticle recommend;
		double recommendNum = Math.random();
		int recommendIntNum = (int) (recommendNum * forPrintGetMovieArticles.size());
		recommend = forPrintGetMovieArticles.get(recommendIntNum);
		System.out.printf("어떤 걸 볼 지 고민되신다면 '%s'(은)는 어떠세요?\n\n", recommend.title);
	}

	private void doTicketing() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}

		List<MovieArticle> forPrintGetMovieArticles = movieArticleService.getMovieArticles();
		MovieArticle movieArticle;

		while (true) {
			System.out.println("예매를 원하시는 해당 영화의 번호를 입력해주세요.");
			System.out.print("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();
			if (selectNum > forPrintGetMovieArticles.size() || selectNum < 0) {
				System.out.println("입력한 번호를 확인 후 다시 입력해주세요.");
				continue;
			}
			movieArticle = forPrintGetMovieArticles.get(selectNum);
			System.out.printf("\n%s(을)를 선택하셨습니다. 예매를 진행하시겠습니까?\n\n", movieArticle.title);
			break;
		}

		while (true) {
			Controller controller = null;
			SeatController seatController = new SeatController(sc);

			System.out.println("1. 예매하기");
			System.out.println("9. 돌아가기");
			System.out.print("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();

			if (selectNum == 9) {
				break;
			} else if (selectNum == 1) {
				controller = seatController;
			}
			else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			controller.doAction(selectNum);
			break;
		}
	}
}