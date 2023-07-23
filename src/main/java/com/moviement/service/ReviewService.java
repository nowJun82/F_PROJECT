package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.ReviewDao;
import com.moviement.dto.Review;

public class ReviewService {
	private ReviewDao reviewDao;

	public ReviewService() {
		reviewDao = Container.reviewDao;
	}
	
	public Review getReview(int id) {
		return reviewDao.getReview(id);
	}
	
	public List<Review> getForPrintReivews() {
		return reviewDao.getReviews();
	}

	public List<Review> getReviews() {
		return reviewDao.getReviews();
	}
}