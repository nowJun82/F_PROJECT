package com.moviement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.moviement.dto.Seat;
import com.moviement.service.SeatService;

public class SeatController extends Controller {
	private Scanner sc;
	private int selectNum;
	private SeatService seatService;
	private Session session;

	public SeatController(Scanner sc) {
		this.sc = sc;
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		switch (selectNum) {
		case 1:
			subTicketing();
			break;
		case 2:

		}
	}

	private void subTicketing() {
		while (true) {
			System.out.println("인원을 입력해주세요.");
			System.out.print("입력 : ");
			int persons = sc.nextInt();
			System.out.println();

			// 여기서부터 해결
			List<Seat> forPrintGetSeats = seatService.getForPrintSeats();

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
}
