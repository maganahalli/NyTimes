package com.mac.nytimes.customDatePicker;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mac.nytimes.R;

/**
 * Adapter for GEICOADte Picker grid Views
 *
 * @author Venky Maganahalli, GEICO
 */
public class GridCellAdapter extends BaseGridCellAdapter {

	private final Activity activity;
	private TextView gridCell;
	private TextView selectedView;

	public GridCellAdapter(int month, int year, CustomDate minimumPaymentDate, CustomDate selectedPayDate, CustomDate
			maximumPaymentDate, Activity activity) {
		super(new DatePickerViewContext(month, year, minimumPaymentDate, selectedPayDate, maximumPaymentDate));
		this.activity = activity;
		buildGeicoDateSelector(month, year);
	}

	protected void considerSettingDefaultDateSelection(String runningDate) {
		CustomDate minimumDate = getCalendarContext().getMinimumDate();
		CustomDate selectedDate = getCalendarContext().getSelectedPayDate();
		String defaultDateString = createDefaultSelectionDateString(selectedDate.isLaterThan(minimumDate) ?
				selectedDate : minimumDate);
		if (defaultDateString.equals(runningDate)) {
			gridCell.setBackgroundResource(R.drawable.grid_cell_selected_background);
			gridCell.setTextColor(activity.getResources().getColor(R.color.white));
			selectedView = gridCell;
		}
	}

	protected String createDefaultSelectionDateString(CustomDate date) {
		return "" + date.getDay() + "-ENABLED-" + getCalendarContext().monthAsString(date.getMonthIndex()) + "-" +
				date.getYear();

	}

	@Override
	public int getCount() {
		return getDaysList().size();
	}

	@Override
	public Object getItem(int position) {
		return getDaysList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public TextView getSelectedView() {
		return selectedView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = activity.getLayoutInflater().inflate(R.layout.date_cell, parent, false);
		}
		gridCell = (TextView) row.findViewById(R.id.dateCell);
		String[] detailsOfDay = getDaysList().get(position).split("-");
		String day = detailsOfDay[0];
		String month = detailsOfDay[2];
		String year = detailsOfDay[3];
		String cellStatus = detailsOfDay[1];
		setDayStatus(cellStatus);
		gridCell.setText(day);
		gridCell.setTag(day + "-" + month + "-" + year);
		considerSettingDefaultDateSelection(getDaysList().get(position));
		return row;
	}

	protected void setDayStatus(String cellStatus) {
		DatePickerCellStatus status = DatePickerCellStatus.fromString(cellStatus);
		if (!status.isEnabled()) {
			gridCell.setTextColor(Color.BLACK);
			gridCell.setBackgroundColor(activity.getResources().getColor(R.color.cardview_light_background));
			gridCell.setEnabled(false);

		} else {

			gridCell.setTextColor(activity.getResources().getColor(R.color.cardview_dark_background));
			gridCell.setBackgroundResource(R.drawable.grid_cell_background);
		}
	}

}