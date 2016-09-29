package com.mac.nytimes.customDatePicker;

import java.util.Date;

import android.view.View;

/**
 * GEICO Date Picker event listener
 *
 * @author Venky Maganahalli
 */
public interface DatePickerEventListener {

	/**
	 * Inform client user has clicked on a date
	 *
	 * @param date
	 * @param view
	 */
	void onSelectDate(Date date, View view);

}
