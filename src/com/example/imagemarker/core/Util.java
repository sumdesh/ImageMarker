package com.example.imagemarker.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.imagemarker.enums.DateFormat;

public class Util {

	public static Date getSystemDate(DateFormat format){
		Date now = new Date();
		try {
			switch (format) {
			case ISO_8601:			
				return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(now.toString());

			case GERMAN:
				return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).parse(now.toString());

			default:
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(now.toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now;
	}
}
