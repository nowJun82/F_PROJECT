package com.moviement.container;

import com.moviement.controller.Session;
import com.moviement.dao.MemberDao;
import com.moviement.dao.MovieArticleDao;
import com.moviement.db.DBConnection;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;

public class Container {
	public static Session session;
	public static DBConnection dbConnection;
	public static MovieArticleDao movieArticleDao;
	public static MemberDao memberDao;
	public static MovieArticleService movieArticeService;
	public static MemberService memberService;

	static {
		memberDao = new MemberDao();
		memberService = new MemberService();
		movieArticleDao = new MovieArticleDao();
		movieArticeService = new MovieArticleService();

	}

	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}
}