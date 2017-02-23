package com.mac.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mac.nytimes.R;

public class BasesActivity extends AppCompatActivity {

	String CODE1TEXT = "public int toDecimal(int n, int b)\n" +
			"{\n" +
			"   int result=0;\n" +
			"   int multiplier=1;\n" +
			"      \n" +
			"   while(n>0)\n" +
			"   {\n" +
			"      result+=n%10*multiplier;\n" +
			"      multiplier*=b;\n" +
			"      n/=10;\n" +
			"   }\n" +
			"      \n" +
			"   return result;\n" +
			"}";
	String CODE2TEXT = "43/2 = 21 + remainder 1\n" +
			"21/2 = 10 + remainder 1\n" +
			"10/2 = 5  + remainder 0\n" +
			"5/2  = 2  + remainder 1\n" +
			"2/2  = 1  + remainder 0\n" +
			"1/2  = 0  + remainder 1";

	String CODE3TEXT = "public int fromDecimal(int n, int b)\n" +
			"{\n" +
			"   int result=0;\n" +
			"   int multiplier=1;\n" +
			"      \n" +
			"   while(n>0)\n" +
			"   {\n" +
			"      result+=n%b*multiplier;\n" +
			"      multiplier*=10;\n" +
			"      n/=b;\n" +
			"   }\n" +
			"      \n" +
			"   return result;\n" +
			"}";

	String CODE4TEXT = "public String fromDecimal2(int n, int b)\n" +
			"{\n" +
			"   String chars=\"0123456789ABCDEFGHIJ\";\n" +
			"   String result=\"\";\n" +
			"      \n" +
			"   while(n>0)\n" +
			"   {\n" +
			"      result=chars.charAt(n%b) + result;\n" +
			"      n/=b;\n" +
			"   }\n" +
			"      \n" +
			"   return result;\n" +
			"}";

	String CODE5TEXT = "Integer.toBinaryString(n);\n" +
			"Integer.toOctalString(n);\n" +
			"Integer.toHexString(n);";

	TextView code1View;
	TextView code2View;
	TextView code3View;
	TextView code4View;
	TextView code5View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bases_activity);
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
	}

}
