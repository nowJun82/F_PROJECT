package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.MovieArticleDao;
import com.moviement.dto.MovieArticle;

public class MovieArticleService {
	private MovieArticleDao movieArticleDao;

	public MovieArticleService() {
		movieArticleDao = Container.movieArticleDao;
	}

	public List<MovieArticle> getForPrintMovieArticles() {
		return movieArticleDao.getMovieArticles();
	}

	public int doWriteMovieList(String title, int price) {
		MovieArticle movieArticle = new MovieArticle(title, price);
		return movieArticleDao.doWriteMovieList(movieArticle);
	}

	public int doWriteMovieList(String title, String body, int price) {
		MovieArticle movieArticle = new MovieArticle(title, body, price);
		return movieArticleDao.doWriteMovieList(movieArticle);
	}

	public List<MovieArticle> getMovieArticles() {
		return movieArticleDao.getMovieArticles();
	}

	public MovieArticle getMovieArticle(int id) {
		return movieArticleDao.getMovieArticle(id);
	}
}