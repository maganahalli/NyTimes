package com.mac.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mac.nytimes.R;

public class GcdActivity extends AppCompatActivity {

	String CODE1TEXT = " for (int i=Math.min(a,b); i>=1; i--)" + "\n" +
			"if (a%i==0 && b%i==0)" + "\n" +
			"return i;" + "\n";
	String CODE2TEXT = "//assume that a and b cannot both be 0\n" +
			"public int GCD(int a, int b)\n" +
			"{\n" +
			"   if (b==0) return a;\n" +
			"   return GCD(b,a%b);\n" +
			"}";

	String CODE3TEXT = "public int LCM(int a, int b)\n" +
			"{\n" +
			"   return b*a/GCD(a,b);\n" +
			"}";

	TextView code1View;
	TextView code2View;
	TextView code3View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gcd_activity);
		code1View = (TextView) findViewById(R.id.code1);
		code1View.setText(CODE1TEXT);
		code2View = (TextView) findViewById(R.id.code2);
		code2View.setText(CODE2TEXT);
		code3View = (TextView) findViewById(R.id.code3);
		code3View.setText(CODE3TEXT);
	}

}
