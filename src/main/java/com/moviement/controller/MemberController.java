package com.moviement.controller;

import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.service.MemberService;

public class MemberController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
	private Session session;

	public MemberController(Scanner isc) {
		this.sc = isc;
		memberService = Container.memberService;
		session = Container.getSession();
	}

	public void doAction(int selectNum)s {
		this.selectNum = selectNum;

		System.out.printf("=== === === M E M B E R === === ===\n\n");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("4. 마이 페이지");
		System.out.printf("9. 이전 단계로\n\n");
		System.out.printf("선택 : ");
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
			System.out.println("다시 입력해주세요.");
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


	private void doJoin() {
		String loginId = null;

		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.next();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.next();
			System.out.printf("비밀번호 확인 : ");
			loginPwConfirm = sc.next();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.next();

		memberService.join(loginId, loginPw, name);

		System.out.printf("%s님, MovieMent 회원이 되신걸 환영합니다 :D\n", name);
	}
	
	public void doLogin() {
		if (Container.getSession().isLogined()) {
			System.out.println("이미 로그인 되어있습니다.\n");
			return;
		}
		System.out.printf("=== === === L O G I N === === ===\n\n");
		System.out.printf("아이디 : ");
		String loginId = sc.next();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.next();
		System.out.println();
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}
		
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		
		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();
		
		System.out.printf("어서 오세요 %s님, 환영합니다 :D\n\n", loginedMember.name);
	}
	
	private void doLogout() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
		session.setLoginedMember(null);
		System.out.println("로그아웃 되었습니다.");
	}

	private void showMyPage() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
	}
}