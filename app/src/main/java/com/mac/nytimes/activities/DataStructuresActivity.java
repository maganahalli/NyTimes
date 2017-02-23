package com.mac.nytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mac.nytimes.R;

public class DataStructuresActivity extends AppCompatActivity {

	static public final String ARG_TEXT_ID = "text_id";

	public void onClickHelp(View v) {
		int id = v.getId();
		int textId = -1;
		switch (id) {
			case R.id.simpleDataStructure:
				textId = R.string.section1;
				break;
			case R.id.arrays:
				textId = R.string.section2;
				break;
			case R.id.linkedLists:
				textId = R.string.section3;
				break;
			case R.id.queues:
				textId = R.string.section4;
				break;
			case R.id.stacks:
				textId = R.string.section5;
				break;
			case R.id.trees:
				textId = R.string.section6;
				break;
			case R.id.btree:
				textId = R.string.section7;
				break;
			case R.id.pqueues:
				textId = R.string.section8;
				break;
			case R.id.hashtables:
				textId = R.string.section9;
				break;
			default:
				break;
		}

		if (textId >= 0) startInfoActivity(textId);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_structures_activity);
	}

	public void startInfoActivity(int textId) {
		if (textId >= 0) {
			Intent intent = (new Intent(this, HelpActivity.class));
			intent.putExtra(ARG_TEXT_ID, textId);
			startActivity(intent);
		}
	} // end startInfoActivity

}
