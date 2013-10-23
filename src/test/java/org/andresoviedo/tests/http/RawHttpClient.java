package org.andresoviedo.tests.http;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RawHttpClient {

	public class DbCar {

		public DbCar(ResultSet rs) {
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy");
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse("Thu Jan 26 15:05:48 COT 2012");
			System.out.println(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		List<Object> items = null;
//		ResultSet rs = null;
//		while (rs.next()) {
//			items.add(new DbCar(rs)).buildObject();
//		}

	}
}
