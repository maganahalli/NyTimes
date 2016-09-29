package com.mac.nytimes.customDatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Models modeling dates without time using the calendar class as the internal
 * representation.
 * <p>
 * This class is thread-safe and immutable.
 *
 * @author Venky
 */
public class CustomCalendarDate extends CustomBaseDate {

	/**
	 * Create a date that is the current day in "America/New_York" time-zone.
	 * Internally represented as midnight morning of the day in
	 * "America/New_York" time-zone.
	 *
	 * @return an AceDate, never null
	 */
	public static CustomDate createToday() {
		Calendar calendar = Calendar.getInstance(GEICO_TIME_ZONE, Locale.US);
		return new CustomCalendarDate(calendar);
	}

	/**
	 * Convert the supplied java.util.date to an AceDate.
	 *
	 * @param calendar to be converted, must not be null
	 * @return an AceDate, never null
	 */
	public static CustomDate fromCalendar(Calendar calendar) {
		Calendar copy = Calendar.getInstance(GEICO_TIME_ZONE, Locale.US);
		copy.setTimeInMillis(calendar.getTimeInMillis());
		return new CustomCalendarDate(copy);
	}

	/**
	 * Convert the supplied java.util.date to an AceDate.
	 * <p>
	 * Note: the date will captured using the time "America/New_York" time-zone,
	 * not the local device time. This may cause the date to change, developers
	 * must take this into consideration.
	 *
	 * @param date to be converted, must not be null
	 * @return an AceDate, never null
	 */
	public static CustomDate fromGeicoDate(Date date) {
		Calendar calendar = Calendar.getInstance(GEICO_TIME_ZONE, Locale.US);
		calendar.setTime(date);
		return new CustomCalendarDate(calendar);
	}

	public static CustomDate fromMilliseconds(long millisecondsSinceEpoch) {
		Calendar calendar = Calendar.getInstance(GEICO_TIME_ZONE, Locale.US);
		calendar.setTimeInMillis(millisecondsSinceEpoch);
		return new CustomCalendarDate(calendar);
	}

	private final Calendar calendar;

	/**
	 * Protected constructor
	 * <p>
	 * Note a clone is made to ensure immutablity. If the original calendar
	 * changes this object will not change.
	 *
	 * @param calendar must not be null, will be modified, caller must not keep
	 *            a reference
	 */
	protected CustomCalendarDate(Calendar calendar) {
		calendar.setTimeZone(GEICO_TIME_ZONE);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		this.calendar = calendar;
	}

	/**
	 * Get a new date with the value added to the supplied field.
	 * <p>
	 * IMPORTANT this date will not be changed since it is immutable.
	 *
	 * @param field the calendar field to modify for example: Calendar.YEAR
	 * @param days to add
	 * @return an AceDate, never null, may be AceUnnownDate
	 */
	protected CustomDate add(int field, int value) {
		Calendar clone = asCalendar();
		clone.add(field, value);
		return new CustomCalendarDate(clone);
	}

	@Override
	public CustomDate addDays(int days) {
		return add(Calendar.DAY_OF_MONTH, days);
	}

	@Override
	public CustomDate addMonths(int months) {
		return add(Calendar.MONTH, months);
	}

	@Override
	public CustomDate addYears(int years) {
		return add(Calendar.YEAR, years);
	}

	@Override
	public Calendar asCalendar() {
		// Note a clone is made to ensure immutablity. If the calling code
		// changes the calendar, this object will not change. - cmd
		return (Calendar) calendar.clone();
	}

	@Override
	public Date asDate() {
		return calendar.getTime();
	}

	@Override
	public String asLongString() {
		return asString("MMMM dd, yyyy");
	}

	@Override
	public long asMilliseconds() {
		return calendar.getTimeInMillis();
	}

	@Override
	public String asMonthDayString() {
		return asString("MMMM dd");
	}

	@Override
	public String asShortString() {
		return asString("MMM dd, yyyy");
	}

	@Override
	public String asString(String template) {
		DateFormat formatter = new SimpleDateFormat(template, Locale.US);
		formatter.setTimeZone(calendar.getTimeZone());
		return formatter.format(calendar.getTime());
	}

	@Override
	public String asXsdDateOrNull() {
		return asString("yyyy-MM-dd");
	}

	@Override
	public int beSubtractedFromKnownDate(CustomDate laterDate, int defaultValue) {
		int bestGuess = (int) ((laterDate.asMilliseconds() - asMilliseconds()) / MILLISECONDS_IN_TYPICAL_DAY);
		Calendar guessCalendar = laterDate.asCalendar();
		guessCalendar.add(Calendar.DATE, -bestGuess);
		int comparison = calendar.compareTo(guessCalendar);
		while (comparison != 0) {
			bestGuess -= comparison;
			guessCalendar.add(Calendar.DATE, comparison);
			comparison = guessCalendar.compareTo(calendar);
		}
		return bestGuess;
	}

	@Override
	public int daysInFuture(int defaultValue) {
		return createToday().beSubtractedFromKnownDate(this, defaultValue);
	}

	@Override
	public boolean equals(Object another) {
		return this == another || isSame(another);
	}

	@Override
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public int getDaysInMonth() {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	@Override
	public int getDaysInNextMonth() {
		return addMonths(1).getDaysInMonth();
	}

	@Override
	public int getDaysInPreviousMonth() {
		return addMonths(-1).getDaysInMonth();
	}

	@Override
	public int getMonthIndex() {
		return calendar.get(Calendar.MONTH);
	}

	@Override
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	@Override
	public int hashCode() {
		return calendar.hashCode();
	}

	/**
	 * Answer whether the supplied object is equivalent to this instance.
	 *
	 * @param another to be compared
	 * @return true/false
	 */
	protected boolean isSame(Object another) {
		return another != null && another instanceof CustomCalendarDate
				&& calendar.equals(((CustomCalendarDate) another).calendar);
	}

	@Override
	public CustomDate maximum(CustomDate anotherDate) {
		return anotherDate.isKnown() && isLaterThan(anotherDate) ? this : anotherDate;
	}

	@Override
	public CustomDate minimum(CustomDate anotherDate) {
		return anotherDate.isKnown() && isEarlierThan(anotherDate) ? this : anotherDate;
	}

	@Override
	public int monthChangesSince(CustomDate earlierDate) {
		if (!earlierDate.isKnown()) {
			return 0;
		}
		return (getYear() - earlierDate.getYear()) * 12 + (getMonthIndex() - earlierDate.getMonthIndex());
	}

	@Override
	public int subtract(CustomDate earlierDate, int defaultValue) {
		return earlierDate.beSubtractedFromKnownDate(this, 0);
	}

	@Override
	public CustomDate today() {
		return createToday();
	}

}
