package com.polsri.union.app.util.core;

import java.sql.Date;

public class Utility {

	public static java.util.Date convertToUtilDate(Date date) {
		if (date == null) {
			return null;
		} else {
			return new java.util.Date(date.getTime());
		}
	}

}
