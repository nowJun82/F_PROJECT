package com.moviement.container;

import com.moviement.dao.MemberDao;
import com.moviement.db.DBConnection;

public class Container {
	public static DBConnection dbConnection;
//	public static MovieArticleDao articleDao;
	public static MemberDao memberDao;
//	public static MovieArticleService articeService;
//	public static MemberService memberService;

	static {
		memberDao = new MemberDao();
	}

	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
}