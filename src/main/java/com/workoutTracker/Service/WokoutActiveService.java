package com.workoutTracker.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WokoutActiveService {

	public long timeDifference(String a, String b) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(a);
		Date date2 = format.parse(b);
		long difference = date2.getTime() - date1.getTime();
		long result = difference / 1000;
		return result;

	}

	public int calculateCalories(long value) {
		int count = 0;

		if (value > 2) {

			if (value > 60) {
				count = 25;
			} else if (value > 40) {
				count = 17;
			} else if (value > 20) {
				count = 12;
			} else if (value > 10) {
				count = 6;
			}
		} else {
			count = 1;

		}
		return count;

	}

}
