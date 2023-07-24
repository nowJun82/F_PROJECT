package com.moviement.container;

import com.moviement.controller.Session;
import com.moviement.dao.MemberDao;
import com.moviement.dao.MovieArticleDao;
import com.moviement.dao.ReviewDao;
import com.moviement.dao.SeatDao;
import com.moviement.db.DBConnection;
import com.moviement.service.MemberService;
import com.moviement.service.MovieArticleService;
import com.moviement.service.ReviewService;
import com.moviement.service.SeatService;

public class Container {
	public static Session session;
	public static DBConnection dbConnection;
	public static MovieArticleDao movieArticleDao;
	public static MemberDao memberDao;
	public static ReviewDao reviewDao;
	public static SeatDao seatDao;
	public static MovieArticleService movieArticleService;
	public static MemberService memberService;
	public static ReviewService reviewService;
	public static SeatService seatService;

	static {
		memberDao = new MemberDao();
		memberService = new MemberService();
		movieArticleDao = new MovieArticleDao();
		movieArticleService = new MovieArticleService();
		reviewDao = new ReviewDao();
		reviewService = new ReviewService();
		seatDao = new SeatDao();
		seatService = new SeatService();
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