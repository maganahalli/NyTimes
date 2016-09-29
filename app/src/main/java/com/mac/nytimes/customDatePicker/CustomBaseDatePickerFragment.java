package com.mac.nytimes.customDatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.mac.nytimes.R;

import static com.mac.nytimes.R.color.white;
import static com.mac.nytimes.R.drawable;
import static com.mac.nytimes.R.id;
import static com.mac.nytimes.R.layout;

/**
 * Created by u1d090 on 9/29/2016.
 */

public abstract class CustomBaseDatePickerFragment extends Fragment implements DatePickerEventListener {

	private GridCellAdapter dateAdapter;
	private DatePickerEventListener datePickerEventListener;
	private final OnItemClickListener dateItemClickListener = createOnItemClickListener();
	private GridView datePickerView;
	private TextView selectedView;
	private GridView weekDaysView;

	protected void considerReSettingDefaultDateSelection(String selectedDateString) {
		TextView selectedDefaultView = dateAdapter.getSelectedView();
		if (selectedDefaultView == null || getDefaultSelectedDateString(selectedDefaultView).equals
				(selectedDateString)) {
			return;
		}
		selectedDefaultView.setTextColor(Color.WHITE);
		selectedDefaultView.setBackgroundResource(drawable.grid_cell_background);

	}

	protected DatePickerEventListener createDatePickerListener() {
		return new DatePickerEventListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				updatePreviousSelectedDateView(view);
				selectedView = (TextView) view;
			}
		};
	}

	protected OnItemClickListener createOnItemClickListener() {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String dateString = dateAdapter.getDaysList().get(position);
				updateSelectedDate(view, dateString);
			}
		};
	}

	protected String getDefaultSelectedDateString(TextView selectedDefaultView) {
		return (String) selectedDefaultView.getTag();
	}

	protected String getPreviouslySelectedDateString(TextView selectedPreviousView) {
		return (String) selectedPreviousView.getTag();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setDatePickerEventListener(createDatePickerListener());
		updateAdapter();

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(layout.custom_date_picker_view, container, false);
		weekDaysView = (GridView) fragmentView.findViewById(id.weekday_gridview);
		datePickerView = (GridView) fragmentView.findViewById(id.daysgridview);
		datePickerView.performClick();
		return fragmentView;
	}

	protected Date parseDateText(String dateString) {
		try {
			return new SimpleDateFormat("dd-MMM-yyyy", Locale.US).parse(dateString);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public void setDatePickerEventListener(DatePickerEventListener datePickerEventListener) {
		this.datePickerEventListener = datePickerEventListener;
	}

	public void updateAdapter() {
		dateAdapter = updateGridCellAdapter();
		dateAdapter.notifyDataSetChanged();
		datePickerView.setAdapter(dateAdapter);
		datePickerView.setOnItemClickListener(dateItemClickListener);
		weekDaysView.setAdapter(new WeekDayGridCellAdapter(getActivity()));
	}

	public abstract GridCellAdapter updateGridCellAdapter();

	protected void updatePreviousSelectedDateView(View view) {
		String selectedDateString = (String) view.getTag();
		if (selectedView == null || getPreviouslySelectedDateString(selectedView).equals(selectedDateString)) {
			return;
		}
		selectedView.setBackgroundResource(R.color.white);
		selectedView.setTextColor(R.color.yourBillingColor);
	}

	protected void updateSelectedDate(TextView view, String selectedDate) {
		String[] detailsOfDay = selectedDate.split("-");
		String cellStatus = detailsOfDay[1];
		DatePickerCellStatus cellsStatus = DatePickerCellStatus.fromString(cellStatus);

		if (cellsStatus.isEnabled()) {
			String selectedDateString = (String) view.getTag();
			considerReSettingDefaultDateSelection(selectedDateString);
			Date selectedDateObject = parseDateText(selectedDateString);
			view.setBackgroundResource(drawable.grid_cell_selected_background);
			view.setTextColor(white);
			datePickerEventListener.onSelectDate(selectedDateObject, view);
		}
	}

	protected void updateSelectedDate(View view, String selectedDate) {
		TextView dateCell = (TextView) view.findViewById(id.dateCell);
		updateSelectedDate(dateCell, selectedDate);
	}

}
