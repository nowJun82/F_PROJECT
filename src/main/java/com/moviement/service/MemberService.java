package com.moviement.service;

import com.moviement.container.Container;
import com.moviement.dao.MemberDao;
import com.moviement.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void join(String loginId, String loginPw, String name) {
		Member member = new Member(loginId, loginPw, name);
		memberDao.join(member);
	}

	public String getMemberByNameId(int memberId) {
		return null;
	}
}