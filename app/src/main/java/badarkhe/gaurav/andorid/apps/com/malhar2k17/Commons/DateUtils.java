/**
 * 
 */
package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Gaurav Badarkhe
 * @email gauravbad555@gmail.com
 * @date Apr 3, 2016
 * @time 7:50:47 PM
 */
public class DateUtils {

	static DateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final String FORMAT = "%02d:D %02d:H %02d:M %02d:S";

	public static String parseTime(long milliseconds) {
		if (TimeUnit.MILLISECONDS.toHours(milliseconds) > 24) {
			return String.format(
					FORMAT,
					TimeUnit.MILLISECONDS.toDays(milliseconds),
					(TimeUnit.MILLISECONDS.toHours(milliseconds)-TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds))),
					TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
									.toHours(milliseconds)),
					TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes(milliseconds)));
		} else {
			return String.format(
					FORMAT,
					TimeUnit.MILLISECONDS.toDays(milliseconds),
					TimeUnit.MILLISECONDS.toHours(milliseconds)-TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds)),
					TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
									.toHours(milliseconds)),
					TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes(milliseconds)));
		}

	}

	public static int getDayOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);

		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int getMonthOFYera(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);

		return c.get(Calendar.MONTH)+1;
	}

	public static int getDayOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);

		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static String getMonthForDate(Date d) {
		int num = getMonthOFYera(d);
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getShortMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {

		Calendar from = Calendar.getInstance();
		from.setTimeInMillis(startdate.getTime());
		int mYear = from.get(Calendar.YEAR);
		int mMonth = from.get(Calendar.MONTH) + 1;
		int mDay = from.get(Calendar.DAY_OF_MONTH);

		Calendar till = Calendar.getInstance();
		till.setTimeInMillis(enddate.getTime());
		int tmYear = till.get(Calendar.YEAR);
		int tmMonth = till.get(Calendar.MONTH) + 1;
		int tmDay = till.get(Calendar.DAY_OF_MONTH);

		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	public static Date getxDaysinFuture(int days) {
		Calendar c = Calendar.getInstance();

		c.setTime(new Date()); // Now use today date.

		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static Date getTime(long date, int hour, int min, int sec)
			throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH) + 1;
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);

		Date d = dateFormatter.parse(String.valueOf(mYear + "-" + mMonth + "-"
				+ mDay + " " + hour + ":" + min + ":" + sec));

		return d;
	}

	public static Date getDate(String date) throws ParseException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return dateFormatter.parse(date);
	}

	public static Date getDate(String date, String format)
			throws ParseException {
		DateFormat dateFormatter = new SimpleDateFormat(format);

		return dateFormatter.parse(date);
	}

	public static String getdate(long date, int hour, int min, int sec)
			throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH) + 1;
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);

		return (String.valueOf(mYear + "-" + mMonth + "-" + mDay + " " + hour
				+ ":" + min + ":" + sec));

	}

	public static String getdate(long date) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH) + 1;
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);

		return (String.valueOf(mYear + "-" + mMonth + "-" + mDay + " " + hour
				+ ":" + min + ":" + sec));

	}



}
