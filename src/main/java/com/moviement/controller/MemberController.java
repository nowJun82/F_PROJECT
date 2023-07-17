package com.moviement.controller;

import java.util.Scanner;

import com.moviement.App;
import com.moviement.service.MemberService;

public class MemberController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
//	private Session session;

	public MemberController(Scanner isc) {
		this.sc = isc;
//		memberService = Container.memberService;
//		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		switch (selectNum) {
		case 1:
			doLogin();
			break;
		case 2:
			doJoin();
			break;
		case 3:
			showMyPage();
			break;
		case 9:
			new App().start();
			break;
		case 0:
			System.out.println("저희 MOVIEMENT를 이용해주셔서 감사합니다. 프로그램을 종료합니다.");
			break;
		}
	}

	private boolean isJoinableLoginId(String loingId) {
		return false;
	}

	public void doLogin() {
		System.out.printf("ID : ");
		String loginId = sc.nextLine();
		System.out.printf("PW : ");
		String loginPw = sc.nextLine();

//		Member member = memberService.getMemberByLoginId(loginId);
//		입력받은 아이디에 해당하는 회원이 존재하는 지
//		if (member == null) {
//			System.out.println("해당 회원은 존재하지 않습니다.");
//			return;
//		}
//
//		if (member.loginPw.equals(loginPw) == false) {
//			System.out.println("비밀번호가 맞지 않습니다.");
//			return;
//		}
//
//		session.setLoginedMember(member);
//		Member loginedMember = session.getLoginedMember();
//
//		System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedMember.name);
	}

	private void doJoin() {
		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		memberService.join(loginId, loginPwConfirm, name);

		System.out.printf("회원가입이 완료되었습니다. [%s] 님 환영합니다^^\n", name);
	}

	private void showMyPage() {

	}
}