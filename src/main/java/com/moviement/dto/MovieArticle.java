package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MovieArticle extends Dto {
	public String title;
	public String body;
	public int memberId;
	public int boardId;
	public int hit;
	
	public MovieArticle(int memberId, int boardId, String title, String body) {
		this(memberId, boardId, title, body, 0);
	}
	
	public MovieArticle(int memberId, int boardId, String title, String body, int hit) {
		this.memberId = memberId;
		this.boardId = boardId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
	
	public MovieArticle(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.memberId = (int) row.get("memberId");
		this.boardId = (int) row.get("boardId");
		this.hit = (int) row.get("hit");
	}

	public void increaseHit() {
		hit++;
	}
	
	// 2. 상영 중인 영화 목록 페이지 : movie article
	// 3. 리뷰 & 평점 페이지 : board
	// 4. 영화 추천 페이지 : movie article
	// 5. 영화 예매 페이지 : movie
}