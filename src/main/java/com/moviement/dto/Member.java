package com.moviement.dto;

import java.util.Map;

public class Member extends Dto {
	public String loginId;
	public String loginPw;
	public String name;
	public String eMail;
	public String nickName;
	public String grade;

	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
		this.eMail = (String) row.get("Email");
		this.nickName = (String) row.get("nickName");
		this.grade = (String) row.get("grade");
	}

	public Member(String loginId, String eMail, String nickName, String loginPw, String name, String grade) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.eMail = eMail;
		this.nickName = nickName;
		this.grade = grade;
	}
}