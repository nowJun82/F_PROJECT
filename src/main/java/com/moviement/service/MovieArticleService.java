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

	public int doWriteMovieList(int boardId, String title) {
		MovieArticle movieArticle = new MovieArticle(boardId, title);
		return movieArticleDao.doWriteMovieList(movieArticle);
	}

	public List<MovieArticle> getMovieArticles() {
		return movieArticleDao.getMovieArticles();
	}
}