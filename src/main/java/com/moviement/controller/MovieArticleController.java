package com.moviement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.dto.MovieArticle;
import com.moviement.dto.MovieSeat;
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
		Member loginedMember = Container.getSession().getLoginedMember();

		if (loginedMember.nickName.equals("관리자")) {
			while (true) {
				System.out.println("=== === === A D M I N : M O V I E === === ===");
				System.out.println("\n관리자 전용 페이지입니다.\n");
				System.out.println("상영 중인 영화 목록을 보시려면 1을 입력해주세요.");
				System.out.println("이전으로 돌아가시려면 9를 입력해주세요.");
				System.out.print("입력 : ");
				selectNum = sc.nextInt();
				System.out.println();

				if (selectNum == 1) {
					showMovieList();
					break;
				} else if (selectNum == 9) {
					System.out.println("이전 단계로 돌아갑니다.\n");
					break;
				} else if (selectNum != 1 && selectNum != 9) {
					System.out.println("\n다시 입력해주세요.");
					continue;
				}
			}
		}
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
		Member loginedMember = Container.getSession().getLoginedMember();

		System.out.print("=== === === Movie List === === ===\n\n");
		System.out.println(" 번호 | 제목");

		for (int i = 0; i <= forPrintMovieArticles.size() - 1; i++) {
			movieArticle = forPrintMovieArticles.get(i);

			System.out.printf("%3d | %s\n", movieArticle.id, movieArticle.title);
		}
		System.out.println();

		if (loginedMember.nickName.equals("관리자")) {
			while (true) {
				System.out.println("관리자 전용 페이지입니다.\n");
				System.out.println("상영 중인 영화 목록에 영화 추가를 원하시면 1을 입력해주세요.");
				System.out.println("이전으로 돌아가시려면 9를 입력해주세요.");
				System.out.print("입력 : ");
				selectNum = sc.nextInt();
				System.out.println();

				if (selectNum == 1) {
					doWriteMovieList();
					break;
				} else if (selectNum == 9) {
					System.out.println("이전 단계로 돌아갑니다.\n");
					break;
				} else if (selectNum != 1 && selectNum != 9) {
					System.out.println("\n다시 입력해주세요.");
					continue;
				}
				break;
			}
		}

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
				doTicketing();
				break;
			case 9:
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

		while (true) {
			System.out.println("예매를 원하시는 해당 영화의 번호를 입력해주세요.");
			System.out.print("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();
			if (selectNum > forPrintGetMovieArticles.size() || selectNum < 0) {
				System.out.println("입력한 번호를 확인 후 다시 입력해주세요.");
				continue;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(forPrintGetMovieArticles.get(selectNum - 1).title);
			String movieTitle = String.format(forPrintGetMovieArticles.get(selectNum - 1).title);
			System.out.printf("%s(을)를 선택하셨습니다. 예매를 진행하시겠습니까?\n\n", movieTitle);
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

					List<MovieSeat> getForPrintSeats = seatService.getPrintSeats(movieTitle);

//					MovieSeat getForPrintMovie = seatService.checkSeat(movieTitle);

					System.out.print("   |- --- --- --- --- S C R E E N --- --- --- --- --|\nNo |");
					for (int i = 1; i <= 12; i++) {
						System.out.printf("%3d ", i);
					}
					System.out.print("|\n");
					int k = 0;
					for (int i = 65; i <= 74; i++) {
						System.out.printf("%2c |", (char) i);
						for (int j = 1; j <= 12; j++) {
							try {
								if (getForPrintSeats.get(k).enabledSeat == true) {
									System.out.print(" ■ ");
								}
							} catch (IndexOutOfBoundsException e) {
								System.out.print(" □ ");
							}
							k++;

						}
						System.out.println("|");
					}
					System.out.println("\n예매를 원하는 좌석을 한 개씩 입력해주세요. (□ : 예매 가능 좌석, ■ : 예매 불가 좌석)");

					String selectSeat;
					String selectSeats;

					String[] seatStrArr = new String[persons];
					char[] seatCharArr = new char[persons];
					int[] seatIntArr = new int[persons];

					MovieSeat foundEnabled = null;

					for (int i = 0; i < persons; i++) {
						System.out.printf("%d. 입력 : ", i + 1);
						selectSeat = sc.next();
						selectSeats = selectSeat.toUpperCase();
						seatStrArr[i] = selectSeats;
						seatCharArr[i] = selectSeats.charAt(0);
						seatIntArr[i] = Integer.parseInt(selectSeats.substring(1));

						/*
						 * if (Character.getNumericValue(seatCharArr[i]) == 11) { seatIntArr[i] =
						 * seatIntArr[i] += 12; } else if (Character.getNumericValue(seatCharArr[i]) ==
						 * 12) { seatIntArr[i] = seatIntArr[i] += 24; } else if
						 * (Character.getNumericValue(seatCharArr[i]) == 13) { seatIntArr[i] =
						 * seatIntArr[i] += 36; } else if (Character.getNumericValue(seatCharArr[i]) ==
						 * 14) { seatIntArr[i] = seatIntArr[i] += 48; } else if
						 * (Character.getNumericValue(seatCharArr[i]) == 15) { seatIntArr[i] =
						 * seatIntArr[i] += 60; } else if (Character.getNumericValue(seatCharArr[i]) ==
						 * 16) { seatIntArr[i] = seatIntArr[i] += 72; } else if
						 * (Character.getNumericValue(seatCharArr[i]) == 17) { seatIntArr[i] =
						 * seatIntArr[i] += 84; } else if (Character.getNumericValue(seatCharArr[i]) ==
						 * 18) { seatIntArr[i] = seatIntArr[i] += 96; } else if
						 * (Character.getNumericValue(seatCharArr[i]) == 19) { seatIntArr[i] =
						 * seatIntArr[i] += 108; }
						 */

						foundEnabled = seatService.getForPrintSeat(movieTitle, selectSeat);

						System.err.println(foundEnabled.enabledSeat);

						if (foundEnabled != null) {
							System.out.printf("선택하신 %s 좌석은 이미 예매가 완료된 좌석입니다. 다시 선택해주세요.\n", seatStrArr[i]);
							i--;
							continue;
						}

					}

					System.out.print("\n   |- --- --- --- --- S C R E E N --- --- --- --- --|\nNo |");
					for (int i = 1; i <= 12; i++) {
						System.out.printf("%3d ", i);
					}
					System.out.print("|\n");
					k = 0;
					int y = 0;
					for (int i = 65; i <= 74; i++) {
						System.out.printf("%2c |", (char) i);
						for (int j = 1; j <= 12; j++) {
							if (foundEnabled.enabledSeat == false) {
								if (y < seatIntArr.length) {
									if (seatIntArr[y] == getForPrintSeats.get(k).id) {
										System.out.print(" ▣ ");
										y++;
									} else {
										System.out.print(" □ ");
									}
								} else {
									System.out.print(" □ ");
								}
							} else {
								System.out.print(" ■ ");
							}
							k++;
						}
						System.out.println("|");
					}

					System.out.printf("\n선택하신 좌석은 %s입니다.\n\n", Arrays.toString(seatStrArr));
					System.out.println("1. 예매하기");
					System.out.println("9. 이전 단계로\n");
					System.out.print("입력 : ");
					int yesOrNo = sc.nextInt();
					System.out.println("\n감사합니다. 예매가 완료되었습니다. 예매 내역은 마이 페이지를 확인해주세요.\n");

					switch (yesOrNo) {
					case 1:
						Container.seatService.doTicketing(movieTitle, seatIntArr, seatStrArr);
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
			} else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			break;
		}

	}

	public void doWriteMovieList() {
		String title;
		String body;
		int price;

		System.out.println("=== === === A D M I N : M O V I E : A D D === === ===");
		System.out.println("\n관리자 전용 페이지입니다.\n");

		System.out.println("추가하실 영화의 제목을 입력해주세요.");
		System.out.print("영화 제목 : ");
		title = sc.nextLine();
		System.out.printf("\n상영 중인 영화 목록에 %s를 추가합니다.\n", title);
//
//		System.out.println("\n추가하실 영화의 한 줄 소개를 입력해주세요.");
//		System.out.print("한 줄 소개 : ");
//		body = sc.nextLine();
//		sc.nextLine();
//
//		System.out.println("\n추가하실 영화의 가격을 입력해주세요. (기본 : 15000)");
//		System.out.print("가격 : ");
//		price = sc.nextInt();
//		sc.nextLine();
		System.out.println("\n영화 추가를 완료하였습니다.\n");
		movieArticleService.doWriteMovieList(title, 15000);
	}
}