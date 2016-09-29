package com.mac.nytimes.customDatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Models dates without time.
 * <p>
 * Implementations should be thread-safe and immutable.
 *
 * @author venky
 */
public interface CustomDate extends Comparable<CustomDate> {

	/**
	 * Get a new date based on this date incremented by the specified number of
	 * days.
	 * <p>
	 * IMPORTANT this date will not be changed since it is immutable.
	 *
	 * @param days to add
	 * @return an CustomDate, never null, may be AceUnnownDate
	 */
	CustomDate addDays(int days);

	/**
	 * Get a new date based on this date incremented by the specified number of
	 * months.
	 * <p>
	 * IMPORTANT this date will not be changed since it is immutable.
	 *
	 * @param days to add
	 * @return an CustomDate, never null, may be AceUnnownDate
	 */
	CustomDate addMonths(int months);

	/**
	 * Get a new date based on this date incremented by the specified number of
	 * years.
	 * <p>
	 * IMPORTANT this date will not be changed since it is immutable.
	 *
	 * @param years to add
	 * @return an CustomDate, never null, may be AceUnnownDate
	 */
	CustomDate addYears(int years);

	/**
	 * Convert the date to a calendar object.
	 *
	 * @return a new instance of calendar, never null
	 */
	Calendar asCalendar();

	/**
	 * Convert to a java.util.date.
	 * <p>
	 * Warning: the date will not have a time zone and may change date printed
	 * if rendered in a time zone other than "America/New_York".
	 * <p>
	 * When passing to DTO's please consider using asOptionalDate instead, so
	 * that unknown dates will be represented as null.
	 *
	 * @return a new instance of java.util.Date, never null
	 * @see #asOptionalDate()
	 */
	Date asDate();

	/**
	 * Get the full string form of the date for display to the customer.
	 *
	 * @return for example: "January 19, 2014" or "" for unknown dates
	 */
	String asLongString();

	/**
	 * Get the date as milliseconds since the epoch (1/1/1970) at the first time
	 * in the morning in GEICO's time zone (America/New_York).
	 * <p>
	 * Warning: the date may change date printed if rendered in a time zone
	 * other than "America/New_York".
	 * <p>
	 * Unknown dates will return Long.MAX_VALUE or 9223372036854775807, ie.-
	 * time way out in the future.
	 *
	 * @return for example:
	 */
	long asMilliseconds();

	/**
	 * Get the full month and day for display to the customer.
	 *
	 * @return for example: "January 19" or "" for unknown dates
	 */
	String asMonthDayString();

	/**
	 * Convert to a java.util.date or null if the date is unknown. This is the
	 * preferred mechanism for converting to the mit model.
	 * <p>
	 * Warning: the date will not have a time zone and may change date printed
	 * if rendered in a time zone other than "America/New_York".
	 *
	 * @return a new instance of java.util.Date or null
	 */
	Date asOptionalDate();

	/**
	 * Convert to an abbreviated string form of the date for display to the
	 * customer.
	 *
	 * @return for example: "Jan 19, 2014" or "" for unknown dates
	 */
	String asShortString();

	/**
	 * Format the string based on the supplied template.
	 *
	 * @param template for example: ""yyyy-MM-dd""
	 * @return for example: "2014-01-19"
	 * @see java.text.SimpleDateFormat
	 */
	String asString(String template);

	/**
	 * Convert to a string formatted as MM/dd/yyyy.
	 *
	 * @return for example: "01/19/2014"
	 */
	String asUsShortString();

	/**
	 * Convert to a string formatted as "yyyy-MM-dd".
	 *
	 * @return for example: "2014-01-19", null
	 */
	String asXsdDateOrNull();

	/**
	 * Calculate difference in days between the supplied date. Value will be
	 * negative if this date is really later than the supplied date.
	 * <p>
	 * Calculation takes into consideration abnormalities like day light savings
	 * changes, leap seconds etc.
	 * <p>
	 * Intended only to be used during double dispatching.
	 *
	 * @param earlierDate must not be unknown or null
	 * @param defaultValue to return if this date is unknown
	 * @return a positive or negative integer
	 * @throws RuntimeException if later date is unknown or null
	 */
	int beSubtractedFromKnownDate(CustomDate laterDate, int defaultValue);

	/**
	 * Calculate the number of days will elapsed until this date. Value will be
	 * negative if this date is in the past.
	 * <p>
	 * Calculation takes into consideration abnormalities like day light savings
	 * changes, leap seconds etc.
	 *
	 * @param defaultValue to return if this date is unknown
	 * @return a positive or negative integer
	 */
	int daysInFuture(int defaultValue);

	/**
	 * Get the day of the month.
	 *
	 * @return 1-31
	 */
	int getDay();

	/**
	 * Get the number of days in the month of the date this object represents.
	 *
	 * @return 28-31
	 */
	int getDaysInMonth();

	/**
	 * Get the number of days in the month after the date this object
	 * represents.
	 *
	 * @return 28-31
	 */
	int getDaysInNextMonth();

	/**
	 * Get the number of days in the month before the date this object
	 * represents.
	 *
	 * @return 28-31
	 */
	int getDaysInPreviousMonth();

	/**
	 * Get the 0 based numeric value for the month.
	 *
	 * @return 0-11, January = 0, December = 12
	 */
	int getMonthIndex();

	/**
	 * Get the numeric value for the year.
	 *
	 * @return for example: 2014
	 */
	int getYear();

	/**
	 * Return true if this date is before another date. Unknown dates will be
	 * treated as if they are in the future.
	 *
	 * @param anotherDate to compare
	 * @return true if thisDate < anotherDate
	 */
	boolean isEarlierThan(CustomDate anotherDate);

	/**
	 * Answer whether the date is >= 12am in the morning tomorrow in
	 * America/New_York. Unknown dates will return true.
	 *
	 * @return true / false
	 */
	boolean isFuture();

	/**
	 * Answer whether we have an actual date or are modeling an unspecified
	 * date.
	 *
	 * @return true/false
	 */
	boolean isKnown();

	/**
	 * Return true if this date is after another date. Unknown dates will be
	 * treated as if they are in the future.
	 *
	 * @param anotherDate to compare
	 * @return true if thisDate > anotherDate
	 */
	boolean isLaterThan(CustomDate anotherDate);

	/**
	 * Answer whether the date is < 12am in the morning today in
	 * America/New_York. Unknown dates will return false.
	 *
	 * @return true / false
	 */
	boolean isPast();

	/**
	 * Answer whether the date is >= 12am in the morning and < 12 midnight today
	 * in America/New_York. Unknown dates will return false.
	 *
	 * @return true / false
	 */
	boolean isToday();

	/**
	 * Get the latest of either this date or the supplied date. If either date
	 * is unknown the result will be unknown.
	 *
	 * @param anotherDate to be compared
	 * @return this date or anotherDate
	 */
	CustomDate maximum(CustomDate anotherDate);

	/**
	 * Get the earliest of either this date or the supplied date. If either date
	 * is unknown the result will be unknown.
	 *
	 * @param anotherDate to be compared
	 * @return this date or anotherDate
	 */
	CustomDate minimum(CustomDate anotherDate);

	/**
	 * Computes the number of times the month changed since the supplied date.
	 * <p>
	 * This method only takes years and months into consideration. For example:
	 * (2/1/2013).elapsedMonthsSince(1/31/2013) = 1 (note: 1 month returned even
	 * though there is only 1 day difference)
	 * <p>
	 * Another example: (1/1/2013).elapsedMonths(1/31/2013) = 0
	 *
	 * @param earlierDate to which we will compare
	 * @return an integer may be negative or 0. If either date is unknown 0 will
	 *         be returned.
	 */
	int monthChangesSince(CustomDate earlierDate);

	/**
	 * Calculate the number of days that have elapsed since the supplied
	 * earlierDate. Value will be negative if earlierDate is really later than
	 * this date.
	 * <p>
	 * Calculation takes into consideration abnormalities like day light savings
	 * changes, leap seconds etc.
	 *
	 * @param earlierDate may be unknown, must not be null
	 * @param defaultValue to return if either date is unknown
	 * @return a positive or negative integer
	 */
	int subtract(CustomDate earlierDate, int defaultValue);

	/**
	 * Get a date that is the current day in "America/New_York" time-zone.
	 * Internally represented as midnight morning of the day in
	 * "America/New_York" time-zone.
	 *
	 * @return an CustomDate, never null
	 */
	CustomDate today();

}