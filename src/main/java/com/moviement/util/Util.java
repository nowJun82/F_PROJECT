package com.moviement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Date time = new Date();
		return format.format(time);
	}
}