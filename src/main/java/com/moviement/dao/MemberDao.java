package com.moviement.dao;

import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;

public class MemberDao {
	private DBConnection dbConnection;

	public MemberDao() {
		dbConnection = Container.getDBConnection();
	}
	
	public int join(Member member) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO `member` "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("loginId = '%s', ", member.loginId));
		sb.append(String.format("loginPw = '%s', ", member.loginPw));
		sb.append(String.format("`name` = '%s' ", member.name));

		return dbConnection.insert(sb.toString());
	}
	
	public Member getMemberByLoginId(String loginId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE loginId = '%s' ", loginId));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
			return null;
		}
		return new Member(memberRow);
	}
}