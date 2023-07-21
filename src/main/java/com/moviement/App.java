package com.moviement;

import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.controller.Controller;
import com.moviement.controller.MemberController;
import com.moviement.controller.MovieArticleController;
import com.moviement.controller.ReviewController;
import com.moviement.db.DBConnection;

public class App {
	public App() {
		DBConnection.DB_NAME = "my_first_project";
		DBConnection.DB_USER = "sbsst";
		DBConnection.DB_PASSWORD = "sbs123414";
		DBConnection.DB_PORT = 3306;

		Container.getDBConnection().connect();
	}

	public void start() {
		Scanner isc = new Scanner(System.in);
		MemberController memberController = new MemberController(isc);
		MovieArticleController movieArticleController = new MovieArticleController(isc);
		ReviewController reviewController = new ReviewController(isc);

		while (true) {
			Controller controller = null;

			System.out.printf("=== === === MOVIEMENT === === ===\n\n");
			System.out.println("1. 로그인 & 회원가입");
			System.out.println("2. 상영 중인 영화 목록 & 추천");
			System.out.println("3. 영화 예매하기");
			System.out.println("4. 리뷰 & 평점");
			System.out.printf("0. 종료\n\n");
			System.out.printf("선택 : ");
			int selectNum = isc.nextInt();
			System.out.println();

			if (selectNum == 0) {
				System.out.print("이용해주셔서 감사합니다. 프로그램을 종료합니다.");
				break;
			} else if (selectNum == 1) {
				controller = memberController;
			} else if (1 < selectNum && selectNum < 4) {
				controller = movieArticleController;
			} else if (selectNum == 4) {
				controller = reviewController;
			}
			else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			controller.doAction(selectNum);
		}
	}
}