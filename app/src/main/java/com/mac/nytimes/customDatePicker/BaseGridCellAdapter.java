package com.mac.nytimes.customDatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.BaseAdapter;

/**
 * Provides common behavior for grid cells.
 *
 * @author Venky Maganahalli, GEICO
 */
public abstract class BaseGridCellAdapter extends BaseAdapter {

	private static final String DISABLED = "-DISABLED-";
	private static final String ENABLED = "-ENABLED-";
	private DatePickerViewContext calendarContext;
	private final List<String> currentMonthDaysList = new ArrayList<>();
	private List<String> daysList = new ArrayList<>();
	private final List<String> leadingDaysList = new ArrayList<>();
	private final List<String> runningDaysList = new ArrayList<>();
	private final List<String> trailingDaysList = new ArrayList<>();

	public BaseGridCellAdapter(DatePickerViewContext calendarContext) {
		this.calendarContext = calendarContext;
	}

	protected void addAllDateStrings(int startDay, int month, int year, int daysInMonth, String monthAsString,
			String colorString, List<String> list) {
		for (int i = startDay; i < daysInMonth; i++) {
			String colorCode = considerDisablingNotValidDates(i, month, year, colorString);
			String dayString = String.valueOf(i + 1) + colorCode + monthAsString + "-" + year;
			list.add(dayString);
		}
	}

	protected void addCurrentMonthDays() {
		int daysInMonth = calendarContext.getDaysInMonth();
		int currentMonth = calendarContext.getMonth();
		int year = calendarContext.getYear();
		String monthAsString = calendarContext.monthAsString(currentMonth);
		addAllDateStrings(0, currentMonth, year, daysInMonth, monthAsString, ENABLED, currentMonthDaysList);
	}

	protected void addLeadingMonthDays() {
		runningDaysList.addAll(trailingDaysList);
		runningDaysList.addAll(currentMonthDaysList);
		int nextMonth = calendarContext.getNextMonth();
		int nextYear = calendarContext.getNextYear();
		String monthAsString = calendarContext.monthAsString(nextMonth);
		addAllDateStrings(0, nextMonth, nextYear, determineLeadingDays(), monthAsString, ENABLED, leadingDaysList);
		runningDaysList.addAll(leadingDaysList);
	}

	protected void addPossibleDatesToList() {
		addTrailingMonthDays();
		addCurrentMonthDays();
		addLeadingMonthDays();
		removeUnwantedDays();
	}

	protected void addTrailingMonthDays() {
		int trailingDays = calendarContext.getCalendar().get(Calendar.DAY_OF_WEEK) - 1;
		int daysInPreviousMonth = calendarContext.getDaysInPreviousMonth();
		int previousMonth = calendarContext.getPreviousMonth();
		String monthAsString = calendarContext.monthAsString(previousMonth);
		int previousYear = calendarContext.getPreviousYear();
		buildTrailingDaysList(trailingDays, daysInPreviousMonth, monthAsString, previousYear);
	}

	protected int adjustToStartOfWeek(int enabledDatePosition) {
		return enabledDatePosition - enabledDatePosition % 7;
	}

	protected void buildGeicoDateSelector(int calendarMonth, int calendarYear) {
		MonthYearDeterminer monthYearHandler = MonthYearDeterminer.fromMonthIndex(calendarMonth);
		determineMonthData(monthYearHandler);
	}

	protected void buildTrailingDaysList(int trailingDays, int daysInPreviousMonth, String monthAsString,
			int previousYear) {
		for (int i = 0; i < trailingDays; i++) {
			String dayString = String.valueOf((daysInPreviousMonth - trailingDays + 1) + i) + DISABLED + monthAsString
					+ "-" + previousYear;
			trailingDaysList.add(dayString);
		}
	}

	protected String considerDisablingNotValidDates(int day, int month, int year, String colorString) {
		return validPaymentDate(day + 1, month, year) ? colorString : DISABLED;
	}

	protected int determineLeadingDays() {
		int leadingDays = determineMaximumLeadingDays();
		int days = daysList.size() % 7;
		return leadingDays > days ? leadingDays : days;
	}

	protected int determineMaximumLeadingDays() {
		int nextMonth = calendarContext.getNextMonth();
		int nextYear = calendarContext.getNextYear();
		Calendar maximumDay = calendarContext.getMaximumDate().asCalendar();
		int maximumMonth = maximumDay.get(Calendar.MONTH);
		int maximumYear = maximumDay.get(Calendar.YEAR);
		int leadingMaxDay = maximumDay.get(Calendar.DAY_OF_MONTH);
		return nextMonth == maximumMonth && nextYear == maximumYear ? leadingMaxDay : 0;
	}

	protected void determineMonthData(MonthYearDeterminer monthYearHandler) {

		if (monthYearHandler.isDecember()) {
			initializeDecemberData();
		} else if (monthYearHandler.isJanuary()) {
			initializeJanuaryData();
		} else {
			initializeRestOfMonthsData();
		}

	}

	public DatePickerViewContext getCalendarContext() {
		return calendarContext;
	}

	public List<String> getDaysList() {
		return daysList;
	}

	protected int getEnabledDateStartingPosition() {
		int enabledDatePosition = 0;
		while (runningDaysList.get(enabledDatePosition).contains(DISABLED)) {
			enabledDatePosition++;
		}
		return enabledDatePosition != 0 ? adjustToStartOfWeek(enabledDatePosition) : enabledDatePosition;
	}

	protected void initializeDecemberData() {
		calendarContext.initializeDecemberData();
		addPossibleDatesToList();
	}

	protected void initializeJanuaryData() {
		calendarContext.initializeJanuaryData();
		addPossibleDatesToList();
	}

	protected void initializeRestOfMonthsData() {
		calendarContext.initializeRestOfMonthsData();
		addPossibleDatesToList();
	}

	protected boolean isUnknownDates() {
		CustomDate minimumDay = calendarContext.getMinimumDate();
		CustomDate maximumDay = calendarContext.getMaximumDate();
		return !minimumDay.isKnown() || !maximumDay.isKnown();
	}

	protected boolean isWithinRange(Calendar runningDay) {
		Calendar minimumDay = calendarContext.getMinimumDate().asCalendar();
		Calendar maximumDay = calendarContext.getMaximumDate().asCalendar();
		return runningDay.equals(minimumDay) || runningDay.equals(maximumDay) || runningDay.after(minimumDay)
				&& runningDay.before(maximumDay);
	}

	protected void removeUnwantedDays() {
		int enabledIndex = getEnabledDateStartingPosition();
		daysList = runningDaysList.subList(enabledIndex, runningDaysList.size());
	}

	public void setCalendarContext(DatePickerViewContext calendarContext) {
		this.calendarContext = calendarContext;
	}

	protected boolean validPaymentDate(int day, int month, int year) {
		// Time bug - do not use calendar - cmd
		Calendar runningDay = Calendar.getInstance();
		runningDay.set(Calendar.YEAR, year);
		runningDay.set(Calendar.MONTH, month);
		runningDay.set(Calendar.DAY_OF_MONTH, day);
		return isUnknownDates() || isWithinRange(runningDay);
	}

}
