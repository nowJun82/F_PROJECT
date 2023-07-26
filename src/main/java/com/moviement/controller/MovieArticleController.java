package com.moviement.controller;

import java.util.Arrays;
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

			System.out.printf("%3d | %s\n", movieArticle.boardId, movieArticle.title);
		}
		System.out.println();

		while (true) {
			System.out.printf("1. 영화 추천받기\n");
			System.out.printf("2. 영화 예매하기\n");
			System.out.printf("9. 이전 단계로\n\n");
			System.out.printf("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();

			switch (selectNum) {
			case 1:
				doRecommend();
				continue;
			case 2:
				subTicketing();
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

	private void subTicketing() {
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
			movieArticle = forPrintGetMovieArticles.get(selectNum-1);
			StringBuilder sb = new StringBuilder();
			sb.append(forPrintGetMovieArticles.get(selectNum-1).title);
			String str = String.format(forPrintGetMovieArticles.get(selectNum-1).title);
			System.out.printf("%s(을)를 선택하셨습니다. 예매를 진행하시겠습니까?\n\n", str);
			System.out.println("1. 예매하기");
			System.out.println("9. 돌아가기");
			System.out.print("선택 : ");
			selectNum = sc.nextInt();
			System.out.println();
			
			if (selectNum == 9) {
				break;
			} else if (selectNum == 1) {
				while (true) {
					System.out.println("인원을 입력해주세요.");
					System.out.print("입력 : ");
					int persons = sc.nextInt();
					System.out.println();

					// 여기서부터 해결
					List<String> forPrintGetSeats = seatService.getForPrintSeats();

					System.out.print(" === === === === S C R E E N === === === ===\n\n");
					int k = 0;
					for (int i = 0; i < 10; i++) {
						for (int j = 1; j <= 14; j++) {
							System.out.printf("%2s ", forPrintGetSeats.get(k));
							k++;
						}
						System.out.println();
					}
					String selectSeat;

					String[] seatArr = new String[persons];
					System.out.println("\n예매를 원하는 좌석을 한 개씩 입력해주세요.");
					for (int i = 0; i < persons; i++) {
						System.out.printf("%d. 입력 : ", i + 1);
						selectSeat = sc.next();
						seatArr[i] = selectSeat;
					}
					System.out.println();
					System.out.printf("선택하신 좌석은 %s입니다.\n\n", Arrays.toString(seatArr));
					System.out.println("1. 예매하기");
					System.out.println("9. 이전 단계로\n");
					System.out.print("입력 : ");
					int yesOrNo = sc.nextInt();
					System.out.println("\n감사합니다. 예매가 완료되었습니다. 예매 내역은 마이 페이지를 확인해주세요.\n");

		 			switch (yesOrNo) {
					case 1: // 여기서 진짜 예매 진행
						Container.seatService.doTicketing(str, seatArr);
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
//				doTicketing();
			}
			else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			break;
		}
	}
	
	private void doTicketing() {
//		while (true) {
//			System.out.println("인원을 입력해주세요.");
//			System.out.print("입력 : ");
//			int persons = sc.nextInt();
//			System.out.println();
//
//			// 여기서부터 해결
//			List<String> forPrintGetSeats = seatService.getForPrintSeats();
//
//			System.out.print(" === === === === S C R E E N === === === ===\n\n");
//			int k = 0;
//			for (int i = 0; i < 10; i++) {
//				for (int j = 1; j <= 14; j++) {
//					System.out.printf("%2s ", forPrintGetSeats.get(k));
//					k++;
//				}
//				System.out.println();
//			}
//			String selectSeat;
//
//			String[] seatArr = new String[persons];
//			System.out.println("\n예매를 원하는 좌석을 한 개씩 입력해주세요.");
//			for (int i = 0; i < persons; i++) {
//				System.out.printf("%d. 입력 : ", i + 1);
//				selectSeat = sc.next();
//				seatArr[i] = selectSeat;
//			}
//			System.out.println();
//			System.out.printf("선택하신 좌석은 %s입니다.\n\n", Arrays.toString(seatArr));
//			System.out.println("1. 예매하기");
//			System.out.println("9. 이전 단계로\n");
//			System.out.print("입력 : ");
//			int yesOrNo = sc.nextInt();
//			System.out.println("\n감사합니다. 예매가 완료되었습니다. 예매 내역은 마이 페이지를 확인해주세요.\n");
//
// 			switch (yesOrNo) {
//			case 1: // 여기서 진짜 예매 진행
//				Container.seatService.doTicketing(seatArr);
//				break;
//			case 9:
//				System.out.println("초기 화면으로 돌아갑니다.\n");
//				break;
//			default:
//				System.out.println("다시 입력해주세요.");
//				continue;
//			}
//			break;
//		}
	}
}