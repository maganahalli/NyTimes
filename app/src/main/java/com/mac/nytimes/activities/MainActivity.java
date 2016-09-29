package com.mac.nytimes.activities;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mac.nytimes.R;
import com.mac.nytimes.adapters.NewsAdapter;
import com.mac.nytimes.commons.AppConstants;
import com.mac.nytimes.dto.TopStoriesResponse;
import com.mac.nytimes.interfaces.RestAPIListener;
import com.mac.nytimes.models.Result;
import com.mac.nytimes.network.NYTimesRestAPI;

public class MainActivity extends AppCompatActivity implements RestAPIListener {

	private CoordinatorLayout coordinatorLayout;
	private NewsAdapter mAdapter;
	private Toolbar mToolbar;
	private RecyclerView recyclerView;
	Snackbar snackbar;
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			sendEmptyMediaMsg();
		}
	};

	private void fetchData() {

		findViewById(R.id.progress).setVisibility(View.VISIBLE);
		new NYTimesRestAPI(this).getTopStories();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		coordinatorLayout = (CoordinatorLayout) findViewById(R.id
				.coordinatorLayout);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		mAdapter = new NewsAdapter(this, new ArrayList<Result>());
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setAdapter(mAdapter);

		setToolbar();
		fetchData();

		LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
				new IntentFilter(AppConstants.BROADCAST));
	}

	public void onCustomDatePickerButtonClicked(View view) {

		Intent intent = new Intent(this, CustomDatePickerActivity.class);
		startActivity(intent);
	}

	@Override
	public void onFailure(String localizedMessage) {
		findViewById(R.id.progress).setVisibility(View.GONE);
		showMessage("Oops! something went wrong. \nPlease check your internet connection and retry.");
	}

	@Override
	public void onSuccess(Object responseObj) {
		TopStoriesResponse data = (TopStoriesResponse) responseObj;
		mAdapter.addAll(data.getResults());
		mAdapter.notifyDataSetChanged();
		findViewById(R.id.progress).setVisibility(View.GONE);
		if (snackbar != null) {
			snackbar.dismiss();
			snackbar = null;
		}
	}

	private void sendEmptyMediaMsg() {
		if (snackbar != null) {
			snackbar.dismiss();
			snackbar = null;
		}
		snackbar = Snackbar
				.make(coordinatorLayout, "No media found.", Snackbar.LENGTH_SHORT);

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
		textView.setTextColor(Color.YELLOW);
		snackbar.show();
	}

	private void setToolbar() {

		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
		TextView myTextView = (TextView) findViewById(R.id.app_title);
		myTextView.setTypeface(myTypeface);
	}

	private void showMessage(String message) {
		if (snackbar != null) {
			snackbar.dismiss();
			snackbar = null;
		}
		snackbar = Snackbar
				.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
				.setAction("RETRY", new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						fetchData();
					}
				});

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
		textView.setTextColor(Color.YELLOW);
		snackbar.setActionTextColor(Color.RED);
		snackbar.show();
	}
}
