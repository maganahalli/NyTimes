package com.mac.nytimes.customDatePicker;

import java.util.Date;

import android.view.View;

/**
 * Created by u1d090 on 9/29/2016.
 */

public class CustomDatePickerFragment extends CustomBaseDatePickerFragment {

	protected GridCellAdapter buildAdapter() {
		int month = getCurrentDateTime().getMonthIndex();
		int year = getCurrentDateTime().getYear();
		CustomDate maximumPaymentDate = getCurrentDateTime().addDays(12);
		CustomDate selectedPayDate = getCurrentDateTime().addDays(3);
		return new GridCellAdapter(month, year, getCurrentDateTime(), selectedPayDate, maximumPaymentDate,
				getActivity());
	}

	private CustomDate getCurrentDateTime() {
		return CustomCalendarDate.createToday();
	}

	@Override
	public void onSelectDate(Date date, View view) {
		updatePreviousSelectedDateView(view);
	}

	@Override
	public GridCellAdapter updateGridCellAdapter() {
		return buildAdapter();
	}
}
