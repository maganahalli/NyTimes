package com.mac.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mac.nytimes.R;

public class FractionsActivity extends AppCompatActivity {

	String CODE1TEXT = "public int[] multiplyFractions(int[] a, int[] b)\n" +
			"{\n" +
			"   int[] c={a[0]*b[0], a[1]*b[1]};\n" +
			"   return c;\n" +
			"}";
	String CODE2TEXT = "public int[] addFractions(int[] a, int[] b)\n" +
			"{\n" +
			"   int denom=LCM(a[1],b[1]);\n" +
			"   int[] c={denom/a[1]*a[0] + denom/b[1]*b[0], denom};\n" +
			"   return c;\n" +
			"}";

	String CODE3TEXT = "public void reduceFraction(int[] a)\n" +
			"{\n" +
			"   int b=GCD(a[0],a[1]);\n" +
			"   a[0]/=b;\n" +
			"   a[1]/=b;\n" +
			"}";

	String CODE4TEXT = "m + n\n" +
			"= (a + ib) + (c + id)\n" +
			"= (a + c) + i(b + d)";

	String CODE5TEXT = "m * n\n" +
			"= (a + ib) * (c + id)\n" +
			"= ac + iad + ibc + (i^2)bd\n" +
			"= (ac - bd) + i(ad + bc)";
	String CODE6TEXT = "public int[] multiplyComplex(int[] m, int[] n)\n" +
			"{\n" +
			"   int[] prod = {m[0]*n[0] - m[1]*n[1], m[0]*n[1] + m[1]*n[0]};\n" +
			"   return prod;\n" +
			"}";

	TextView code1View;
	TextView code2View;
	TextView code3View;
	TextView code4View;
	TextView code5View;
	TextView code6View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fractions_activity);
		code1View = (TextView) findViewById(R.id.code1);
		code1View.setText(CODE1TEXT);
		code2View = (TextView) findViewById(R.id.code2);
		code2View.setText(CODE2TEXT);
		code3View = (TextView) findViewById(R.id.code3);
		code3View.setText(CODE3TEXT);
		code4View = (TextView) findViewById(R.id.code4);
		code4View.setText(CODE4TEXT);
		code5View = (TextView) findViewById(R.id.code5);
		code5View.setText(CODE5TEXT);
		code6View = (TextView) findViewById(R.id.code6);
		code6View.setText(CODE6TEXT);
	}

}
