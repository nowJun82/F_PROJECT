package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.dto.MovieSeat;
import com.moviement.dto.Review;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;
import com.moviement.service.ReviewService;
import com.moviement.service.SeatService;

public class MemberController extends Controller {
	private Scanner sc;
	private int selectNum;
	private int selectMovieNum;
	private MemberService memberService;
	private MovieArticleService movieArticleService;
	private ReviewService reviewService;
	private SeatService seatService;
	private Session session;

	public MemberController(Scanner isc) {
		this.sc = isc;
		memberService = Container.memberService;
		movieArticleService = Container.movieArticleService;
		reviewService = Container.reviewService;
		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		System.out.printf("┌─M E M B E R ─────────────────────┐\n│%56s│\n", " ");
		System.out.println("│1. 회원가입                                             │");
		System.out.println("│2. 로그인                                               │");
		System.out.println("│3. 로그아웃                                             │");
		System.out.println("│4. 마이 페이지                                          │");
		System.out.printf("│9. 이전 단계로                                          │\n");
		System.out.print("└────────────────────────────┘\n");
		System.out.printf("  * 선택 : ");
		selectNum = sc.nextInt();
		System.out.println();

		switch (selectNum) {
		case 1:
			doJoin();
			break;
		case 2:
			doLogin();
			break;
		case 3:
			doLogout();
			break;
		case 4:
			showMyPage();
			break;
		case 9:
			break;
		default:
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 다시 입력해주세요.                                   │");
			System.out.println("└────────────────────────────┘\n");
			break;
		}
	}

	private boolean isJoinableLoginId(String loginId) {
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}
		return false;
	}

	private boolean isJoinableEmail(String eMail) {
		Member member = memberService.getMemberByLoginId(eMail);

		if (member == null) {
			return true;
		}
		return false;
	}

	private boolean isJoinableNickName(String nickName) {
		Member member = memberService.getMemberBynickName(nickName);

		if (member == null) {
			return true;
		}
		return false;
	}

	private void doJoin() {
		if (Container.getSession().isLogined()) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 이미 로그인 되어있습니다. 로그아웃 후 이용해주세요.  │");
			System.out.println("└────────────────────────────┘\n");
			return;
		}
		String loginId = null;

		System.out.printf("┌─J O I N ───────────────────────┐\n│%56s│\n", " ");
		System.out.println("│ * 아이디                                               │");
		System.out.println("│ * 비밀번호                                             │");
		System.out.println("│ * 비밀번호 확인                                        │");
		System.out.println("│ * 이메일                                               │");
		System.out.println("│ * 닉네임                                               │");
		System.out.println("│ * 이름                                                 │");
		System.out.print("└────────────────────────────┘\n");
		while (true) {
			System.out.printf("   * 아이디 : ");
			loginId = sc.next();

			if (isJoinableLoginId(loginId) == false) {
				System.out.println("┌────────────────────────────┐");
				System.out.printf("│ * %s(은)는 이미 사용 중인 아이디입니다.               │\n", loginId);
				System.out.println("└────────────────────────────┘");
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("   * 비밀번호 : ");
			loginPw = sc.next();
			System.out.printf("   * 비밀번호 확인 : ");
			loginPwConfirm = sc.next();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 비밀번호를 다시 입력해주세요.                        │");
				System.out.println("└────────────────────────────┘");
				continue;
			}
			break;
		}

		String eMail = null;

		while (true) {
			System.out.printf("   * 이메일 : ");
			eMail = sc.next();

			if (isJoinableEmail(eMail) == false) {
				System.out.println("┌────────────────────────────┐");
				System.out.printf("│ * %s(은)는 이미 사용 중인 이메일입니다.               │\n", eMail);
				System.out.println("└────────────────────────────┘");
				continue;
			}
			break;
		}

		String nickName = null;

		while (true) {
			System.out.printf("   * 닉네임 : ");
			nickName = sc.next();

			if (isJoinableNickName(nickName) == false) {
				System.out.println("┌────────────────────────────┐");
				System.out.printf("│ * %s(은)는 이미 사용 중인 닉네임입니다.               │\n", nickName);
				System.out.println("└────────────────────────────┘");
				continue;
			}
			break;
		}

		System.out.printf("   * 이름 : ");
		String name = sc.next();
		
		String grade = "bronze";

		memberService.join(loginId, eMail, nickName, loginPw, name, grade);
		System.out.println("\n┌────────────────────────────┐");
		System.out.printf("│ * %s님, MovieMent 회원이 되신걸 환영합니다 :D      │\n", name);
		System.out.println("└────────────────────────────┘");
	}

	public void doLogin() {
		if (Container.getSession().isLogined()) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 이미 로그인 되어있습니다.                            │");
			System.out.println("└────────────────────────────┘\n");
			return;
		}
		String loginId;
		String loginPw;
		Member member;
		System.out.printf("┌─L O G I N ──────────────────────┐\n│%56s│\n", " ");
		System.out.println("│ * 아이디                                               │");
		System.out.println("│ * 비밀번호                                             │");
		System.out.print("└────────────────────────────┘\n");
		while (true) {
			System.out.printf("  * 아이디 : ");
			loginId = sc.next();
			System.out.printf("  * 비밀번호 : ");
			loginPw = sc.next();
			System.out.println();
			member = memberService.getMemberByLoginId(loginId);

			if (member == null) {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 해당 회원은 존재하지 않습니다.                       │");
				System.out.println("└────────────────────────────┘\n");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 비밀번호를 다시 확인해주세요.                        │");
				System.out.println("└────────────────────────────┘\n");
				continue;
			}
			break;
		}

		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();
		System.out.println("┌────────────────────────────┐");
		System.out.printf("│ * 어서 오세요 %s님, 환영합니다 :D                  │\n", loginedMember.name);
		System.out.println("└────────────────────────────┘\n");
	}

	private void doLogout() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 로그인 후 이용해주세요.                              │");
			System.out.println("└────────────────────────────┘\n");
			return;
		}
		session.setLoginedMember(null);
		System.out.println("┌────────────────────────────┐");
		System.out.println("│ * 로그아웃 되었습니다.                                 │");
		System.out.println("└────────────────────────────┘\n");
	}

	private void showMyPage() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 로그인 후 이용해주세요.                              │");
			System.out.println("└────────────────────────────┘\n");
			return;
		}

		System.out.printf("┌─M Y P A G E ─────────────────────┐\n│%56s│\n", " ");
		System.out.println("│1. 회원정보 수정                                        │");
		System.out.println("│2. 나의 예매 현황                                       │");
		System.out.println("│3. 나의 리뷰                                            │");
		System.out.println("│9. 이전 단계로                                          │");
		System.out.println("└────────────────────────────┘");
		System.out.print(" * 선택 : ");
		int selectNum = sc.nextInt();
		System.out.println();

		while (true) {
			switch (selectNum) {
			case 1:
				doModify();
				break;
			case 2:
				showTicket();
				break;
			case 3:
				showReviewList();
				break;
			case 9:
				break;
			}
			break;
		}
	}

	private void doModify() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 로그인 후 이용해주세요.                              │");
			System.out.println("└────────────────────────────┘\n");
		}

		System.out.printf("┌─회원 정보 수정────────────────────┐\n│%56s│\n", " ");
		System.out.println("│1. 내 정보                                              │");
		System.out.println("│2. 비밀번호 변경                                        │");
		System.out.println("│3. 이메일 변경                                          │");
		System.out.println("│4. 닉네임 변경                                          │");
		System.out.println("│5. 회원 탈퇴                                            │");
		System.out.println("│9. 이전 단계로                                          │");
		System.out.println("└────────────────────────────┘");

		System.out.print(" * 선택 : ");
		int selectNum = sc.nextInt();
		System.out.println();

		while (true) {
			switch (selectNum) {
			case 1:
				showMyInfo();
				break;
			case 2:
				changeLoginPw();
				break;
			case 3:
				changeEmail();
				break;
			case 4:
				changeNickName();
				break;
			case 5:
				doDelete();
				break;
			case 9:
				showMyPage();
				break;
			}
			break;
		}
	}

	private void showMyInfo() {
		Member loginedMember = Container.getSession().getLoginedMember();
		int selectNum;

		System.out.printf("┌─내 정보 ───────────────────────┐\n│%56s│\n", " ");
		System.out.printf("│ * 가입일   : %-42s│\n", loginedMember.regDate);
		System.out.printf("│ * 닉네임   : %-39s│\n", loginedMember.nickName);
		System.out.printf("│ * 이름     : %-39s│\n", loginedMember.name);
		System.out.printf("│ * 아이디   : %-42s│\n", loginedMember.loginId);
		System.out.printf("│ * 비밀번호 : %-42s│\n", loginedMember.loginPw);
		System.out.printf("│ * Email    : %s@gmail.com                             │\n│%56s│\n", loginedMember.eMail, " ");
		System.out.println("│                                         9. 이전 단계로 │");
		System.out.println("└────────────────────────────┘");

		System.out.print(" * 입력 : ");
		selectNum = sc.nextInt();
		System.out.println();

		while (true) {
			if (selectNum == 9) {
				doModify();
				break;
			} else {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 다시 입력해주세요.                                   │");
				System.out.println("└────────────────────────────┘\n");
				continue;
			}
		}
	}

	private void showTicket() {
		Member loginedMember = Container.getSession().getLoginedMember();
		List<MovieSeat> getForPrintSeat = Container.seatService.getForPrintSeats(loginedMember.nickName);
		int selectNum;

		if (getForPrintSeat.size() == 0) {
			System.out.println("검색결과가 존재하지 않습니다.\n");
			return;
		}

		System.out.printf("┌─나의 예매 현황────────────────────┐\n│%56s│\n", " ");
		MovieSeat seat;

		System.out.print("├────────────────────────────┤\n│번호 │ 좌석 │ 영화 이름                               │\n└────────────────────────────┘");
		for (int i = 0; i <= getForPrintSeat.size() - 1; i++) {
			seat = getForPrintSeat.get(i);
			selectMovieNum = seat.id;
			System.out.printf("\n   %-3d │ %3s  │ %s ", i + 1, seat.seat, seat.movieTitle);
		}
//		System.out.println("\n└────────────────────────────┘\n");

		while (true) {
			try {
			System.out.println("\n┌────────────────────────────┐");
			System.out.println("│ * 취소를 원하는 티켓의 번호를 입력해주세요.            │");
			System.out.println("└────────────────────────────┘");
			System.out.print(" * 입력 : ");
			selectNum = sc.nextInt();
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 존재하지 않는 번호입니다. 확인 후 다시 입력해주세요.\n");
				System.out.println("└────────────────────────────┘");
				continue;
			}
			break;
		}
		System.out.println();

		while (true) {
			seat = getForPrintSeat.get(selectNum - 1);
			System.out.println(" ─────────────────────────────");
			System.out.printf(" %-3d │ %3s  │ %-7s │ %-21s \n", selectNum, seat.seat, seat.nickName, seat.movieTitle);
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 위 티켓을 정말로 취소하시겠습니까?                   │");
			System.out.printf("│ * 이 작업은 되돌릴 수 없습니다.                        │\n");
			System.out.printf("│ * 예매 당시 지불 금액 : %d원                        │\n│%56s│\n", (int)seat.price, " ");
			System.out.println("│1. 예매 취소                                            │");
			System.out.println("│9. 이전 단계로                                          │");
			System.out.println("└────────────────────────────┘");
			System.out.print(" * 입력 : ");
			selectMovieNum = sc.nextInt();
			if (selectMovieNum != 1 && selectMovieNum != 9) {
				System.out.println("┌────────────────────────────┐");
				System.out.println("│ * 다시 입력해주세요.                                   │");
				System.out.println("└────────────────────────────┘\n");
				continue;
			}
			if (selectMovieNum == 1) {
//				seat = getForPrintSeat.get(selectNum-1);
				Container.seatService.doDeleteSeat(seat.id);
				System.out.printf("\n[%s, %s]예매가 취소 되었습니다.\n\n", seat.movieTitle, seat.seat);
				return;
			}
			if (selectMovieNum == 9) {
				return;
			}
		}
	}

	private void showReviewList() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("│ * 로그인 후 이용해주세요.                              │");
			System.out.println("└────────────────────────────┘\n");
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
			System.out.print("\n1. 리뷰 수정\n");
			System.out.print("9. 이전 단계로\n");
			System.out.print("입력 : ");
			selectNum = sc.nextInt();
			System.out.println();
			if (selectNum == 9) {
				break;
			}
			System.out.println("수정을 원하는 리뷰의 번호를 입력해주세요.");
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

	private void changeNickName() {
		Member loginedMember = Container.getSession().getLoginedMember();
		String nickName = null;

		while (true) {
			System.out.printf("변경할 닉네임 입력 : ");
			nickName = sc.next();
			System.out.println();

			if (isJoinableNickName(nickName) == false) {
				System.out.println("이미 사용중인 닉네임입니다.");
				continue;
			}
			break;
		}
		memberService.modifyNickName(loginedMember.id, nickName);
		System.out.println("닉네임이 변경 되었습니다.\n");
	}

	private void changeLoginPw() {
		Member loginedMember = Container.getSession().getLoginedMember();

		String loginedMemberLoginPwConfirm = null;
		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("현재 비밀번호 : ");
			loginedMemberLoginPwConfirm = sc.next();
			if (loginedMemberLoginPwConfirm.equals(loginedMember.loginPw) == false) {
				System.out.println("비밀번호를 다시 확인해주세요.\n");
				continue;
			}
			System.out.printf("변경할 비밀번호 : ");
			loginPw = sc.next();
			System.out.printf("변경할 비밀번호 확인 : ");
			loginPwConfirm = sc.next();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("변경할 비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		memberService.modifyLoginPw(loginedMember.id, loginPw);
		System.out.println("\n비밀번호가 변경 되었습니다.\n");
	}

	private void changeEmail() {
		Member loginedMember = Container.getSession().getLoginedMember();
		String eMail = null;

		while (true) {
			System.out.printf("변경할 Email : ");
			eMail = sc.next();

			if (isJoinableEmail(eMail) == false) {
				System.out.println("\n이미 사용중인 이메일 입니다.");
				continue;
			}
			break;
		}
		memberService.modifyEmail(loginedMember.id, eMail);
		System.out.println("\n이메일이 변경 되었습니다.\n");
	}

	private void doDelete() {
		String confirmToDeleteTrueOrNot;
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}

		Member loginedMember = Container.getSession().getLoginedMember();
		System.out.printf("비밀번호를 입력하세요 : ");
		String loginPw = sc.next();

		if (loginedMember.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		while (true) {
			System.out.print("\n회원 탈퇴 시 복구가 불가능하며, 정보는 모두 사라집니다.\n");
			System.out.print("탈퇴를 원하시면 '회원탈퇴'를 입력해주세요.\n");
			System.out.print("입력 : ");
			confirmToDeleteTrueOrNot = sc.next();

			if (confirmToDeleteTrueOrNot.equals("회원탈퇴") == false) {
				System.out.println("\n다시 입력해주세요.");
				continue;
			}
			if (confirmToDeleteTrueOrNot.equals("회원탈퇴")) {
				memberService.doDelete(loginedMember.id);
				doLogout();
				loginedMember = null;
				System.out.println("\n회원 탈퇴가 정상적으로 처리되었습니다. 이용해주셔서 감사합니다.\n");
				break;
			}
			break;
		}

	}
}