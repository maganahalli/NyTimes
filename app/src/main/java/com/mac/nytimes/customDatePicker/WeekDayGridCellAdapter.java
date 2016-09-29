package com.mac.nytimes.customDatePicker;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mac.nytimes.R;

/**
 * Adapter for GEICOADte Picker grid Views
 *
 *import static android.R.attr.author;

@author Venky Maganahalli
 */
public class WeekDayGridCellAdapter extends BaseAdapter {

	private final Activity activity;
	private final List<String> daysList = buildDayOfWeekAbbreviations();
	private TextView gridCell;

	public WeekDayGridCellAdapter(Activity activity) {
		super();
		this.activity = activity;
	}

	protected List<String> buildDayOfWeekAbbreviations() {
		return Arrays.asList("S", "M", "T", "W", "T", "F", "S");
	}

	@Override
	public int getCount() {
		return daysList.size();
	}

	@Override
	public Object getItem(int position) {
		return daysList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = activity.getLayoutInflater().inflate(R.layout.date_cell, parent, false);
		}
		String day = daysList.get(position);
		gridCell = (TextView) row.findViewById(R.id.dateCell);
		gridCell.setText(day);
		setDayStatus();
		return row;
	}

	protected void setDayStatus() {
		gridCell.setTextColor(Color.BLACK);
		gridCell.setBackgroundColor(activity.getResources().getColor(R.color.white));
		gridCell.setEnabled(false);
	}
}