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

		System.out.print("=== === === Movie List === === ===\n\n");
		System.out.println(" 번호 | 제목");
		for (int i = 0; i <= forPrintMovieArticles.size() - 1; i++) {
			movieArticle = forPrintMovieArticles.get(i);

			System.out.printf("%3d | %s\n", movieArticle.id, movieArticle.title);
		}
		recommend = forPrintMovieArticles.get(recommendIntNum);
		System.out.printf("\n어떤 걸 볼 지 고민되신다면 %s(은)는 어떠세요?\n\n", recommend.title);

		while (true) {
			System.out.println("예매를 원하시면 해당 영화의 번호를 입력해주세요.");
			System.out.print("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();

			if (selectNum == 9) {
				break;
			}
			if (selectNum > forPrintMovieArticles.size() || selectNum < 0) {
				System.out.println("입력한 번호를 확인 후 다시 입력해주세요.");
				continue;
			}
			doTicketing();
			break;
		}
	}

	private void doTicketing() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}

		while (true) {
			System.out.println("인원을 입력해주세요.");
			System.out.print("입력 : ");
			int persons = sc.nextInt();
			
			System.out.print(" === === === === S C R E E N === === === ===\n\n");

			for (int i = 65; i < 75; i++) {
				for (int j = 1; j <= 14; j++) {
					char c = (char) i;
					System.out.printf("%s%d ", c, j);
				}
				System.out.println();
			}
			String selectSeat;
			StringBuilder sb = new StringBuilder();

			System.out.println("\n예매를 원하는 좌석을 한 개씩 입력해주세요.");
			for (int i = 1; i <= persons; i++) {
				System.out.printf("%d. 입력 : ", i);
				selectSeat = sc.next();
				sb.append(selectSeat + ", ");
			}
			sb.deleteCharAt(sb.length() - 2);
			System.out.println();
			System.out.printf("선택하신 좌석은 %s입니다.\n\n", sb);
			System.out.println("1. 예매하기");
			System.out.println("9. 이전 단계로\n");
			System.out.print("입력 : ");
			int yesOrNo = sc.nextInt();

			switch (yesOrNo) {
			case 1:	// 여기서 진짜 예매 진행
				System.out.println("dddd\n");
				break;
			case 9:
				System.out.println("초기 화면으로 돌아갑니다.\n");
				break;
			default:
				System.out.println("다시 입력해주세요.");
				continue;
			}
			break;
		}
	}
}