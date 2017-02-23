package com.mac.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mac.nytimes.R;

public class GeometryActivity extends AppCompatActivity {

	String CODE1TEXT = "V - E + F = 2, where " + "\n" +
			" 	V = number of vertices " + "\n" +
			"E = number of edges" + "\n" +
			"F = number of faces";

	String CODE3TEXT = "(n) - (E - G) + (F - G + 1) = 2\n" +
			"thus (n+1) - E + F = 2";

	TextView code1View;
	TextView code3View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geometry_activity);
		code1View = (TextView) findViewById(R.id.code1);
		code1View.setText(CODE1TEXT);
		code3View = (TextView) findViewById(R.id.code3);
		code3View.setText(CODE3TEXT);
	}

}
