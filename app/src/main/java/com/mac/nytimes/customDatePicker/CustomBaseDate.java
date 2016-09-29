package com.mac.nytimes.customDatePicker;

import java.util.Date;
import java.util.TimeZone;

/**
 * Provides common behavior for modeling dates without time.
 * <p>
 * Subclasses must be thread-safe and immutable.
 *
 * @author venky
 */
public abstract class CustomBaseDate implements CustomDate {

	/**
	 * Matches the time zone used by the GEICO servers.
	 * <p>
	 * "The US/Eastern time zone has been deemed obsolete. It has been replaced by America/New_York."
	 *
	 * @see <code>http://www.timezoneconverter.com/cgi-bin/zoneinfo.tzc?s=default&tz=US/Eastern</code>
	 */
	public static final TimeZone GEICO_TIME_ZONE = TimeZone.getTimeZone("America/New_York");

	protected static final long MILLISECONDS_IN_HOUR = 60 * 60 * 1000L;

	protected static final long MILLISECONDS_IN_TYPICAL_DAY = MILLISECONDS_IN_HOUR * 24;

	@Override
	public Date asOptionalDate() {
		return asDate();
	}

	@Override
	public String asUsShortString() {
		return asString("MM/dd/yyyy");
	}

	@Override
	public int compareTo(CustomDate another) {
		Long timeInMilliseconds = asMilliseconds();
		return timeInMilliseconds.compareTo(another.asMilliseconds());
	}

	@Override
	public boolean isEarlierThan(CustomDate anotherDate) {
		return this.asMilliseconds() < anotherDate.asMilliseconds();
	}

	@Override
	public boolean isFuture() {
		return this.compareTo(today()) > 0;
	}

	@Override
	public boolean isKnown() {
		return true;
	}

	@Override
	public boolean isLaterThan(CustomDate anotherDate) {
		return this.asMilliseconds() > anotherDate.asMilliseconds();
	}

	@Override
	public boolean isPast() {
		return this.compareTo(today()) < 0;
	}

	@Override
	public boolean isToday() {
		return this.compareTo(today()) == 0;
	}

}
