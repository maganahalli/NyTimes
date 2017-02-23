package com.mac.nytimes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.mac.nytimes.R;

/**
 * Created by U1D090 on 2/9/2017.
 */

public class HelpActivity extends Activity {

	int mTextResourceId = 0;

	/**
	 * onCreate
	 *
	 * @param savedInstanceState Bundle
	 */

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		// Read the arguments from the Intent object.
		Intent in = getIntent();
		mTextResourceId = in.getIntExtra(DataStructuresActivity.ARG_TEXT_ID, 0);
		if (mTextResourceId <= 0){
			mTextResourceId = R.string.no_help_available;
		}

		TextView textView = (TextView) findViewById(R.id.topic_text);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		textView.setText(Html.fromHtml(getString(mTextResourceId)));
	}

} // end class
