package com.mac.nytimes.customDatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * GEICO date picker base date calculation object.
 *
 * @author Venky Maganahalli
 */
public class DatePickerViewContext {

	private final GregorianCalendar calendar;
	private int day = 0;
	private int daysInMonth = 0;
	private int daysInPreviousMonth = 0;
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private final CustomDate maximumDate;
	private final CustomDate minimumDate;
	private int month = 0;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December"};
	private int nextMonth = 0;
	private int nextYear = 0;
	private int previousMonth = 0;
	private int previousYear = 0;
	private CustomDate selectedPayDate;
	private int year = 0;

	public DatePickerViewContext(int month, int year, CustomDate minimumDate, CustomDate maximumDate) {
		this(month, year, 1, minimumDate, maximumDate);
	}

	public DatePickerViewContext(int month, int year, CustomDate minimumDate, CustomDate selectedPayDate, CustomDate
			maximumDate) {
		this(month, year, 1, minimumDate, selectedPayDate, maximumDate);
	}

	public DatePickerViewContext(int month, int year, int day, CustomDate minimumDate, CustomDate maximumDate) {
		this.calendar = new GregorianCalendar(year, month, day);
		this.month = month;
		this.year = year;
		this.maximumDate = maximumDate;
		this.minimumDate = minimumDate;
		this.selectedPayDate = minimumDate;
		setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
	}

	public DatePickerViewContext(int month, int year, int day, CustomDate minimumDate, CustomDate selectedPayDate,
			CustomDate maximumDate) {
		this(month, year, 1, minimumDate, maximumDate);
		this.selectedPayDate = selectedPayDate;
	}

	public int determineNumberOfDaysInMonth(int monthIndex) {
		int days = numberOfDaysInMonth(monthIndex);
		return days + leapYearDateAdjustment();
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public int getDay() {
		return day;
	}

	public int getDaysInMonth() {
		return daysInMonth;
	}

	public int getDaysInPreviousMonth() {
		return daysInPreviousMonth;
	}

	public CustomDate getMaximumDate() {
		return maximumDate;
	}

	public CustomDate getMinimumDate() {
		return minimumDate;
	}

	public int getMonth() {
		return month;
	}

	public int getNextMonth() {
		return nextMonth;
	}

	public int getNextYear() {
		return nextYear;
	}

	public int getPreviousMonth() {
		return previousMonth;
	}

	public int getPreviousYear() {
		return previousYear;
	}

	public CustomDate getSelectedPayDate() {
		return selectedPayDate;
	}

	public int getYear() {
		return year;
	}

	public void initializeDecemberData() {
		previousMonth = 10;
		daysInPreviousMonth = determineNumberOfDaysInMonth(previousMonth);
		daysInMonth = determineNumberOfDaysInMonth(month);
		nextMonth = 0;
		previousYear = year;
		nextYear = year + 1;
	}

	public void initializeJanuaryData() {
		previousMonth = 11;
		daysInPreviousMonth = determineNumberOfDaysInMonth(previousMonth);
		daysInMonth = determineNumberOfDaysInMonth(month);
		nextMonth = 1;
		previousYear = year - 1;
		nextYear = year;
	}

	public void initializeRestOfMonthsData() {
		previousMonth = month - 1;
		nextMonth = month + 1;
		nextYear = year;
		previousYear = year - 1;
		daysInMonth = determineNumberOfDaysInMonth(month);
		daysInPreviousMonth = determineNumberOfDaysInMonth(previousMonth);
	}

	protected int leapYearDateAdjustment() {
		return (calendar.isLeapYear(year) && month == 1) ? 1 : 0;
	}

	public String monthAsString(int index) {
		return months[index];
	}

	public int numberOfDaysInMonth(int index) {
		return daysOfMonth[index];
	}

	public void setCurrentDayOfMonth(int currentDayOfMonth) {
	}

	public void setCurrentWeekDay(int currentWeekDay) {
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
