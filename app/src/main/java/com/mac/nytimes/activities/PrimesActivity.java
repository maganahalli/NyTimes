package com.mac.nytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mac.nytimes.R;

import static com.mac.nytimes.R.id.code2;
import static com.mac.nytimes.R.id.edit2;

public class PrimesActivity extends AppCompatActivity {

	String CODE2TEXT = "public boolean isPrime (int n) {" + "\n" +
			"       if (n <=1) return false;" + "\n" +
			"       if (n==2) return true;" + "\n" +
			"       if (n%2==0) return false;" + "\n" +
			"       int m=Math.sqrt(n);" + "\n" +
			"       for (int i=3; i <=m; i+=2)" + "\n" +
			"          if (n%i==0)" + "\n" +
			"             return false;" + "\n" +
			"       return true;" + "\n" +
			"    }";
	TextView code2View;
	private CoordinatorLayout coordinatorLayout;
	EditText edit1View;
	TextView result;
	Snackbar snackbar;

	protected boolean isPrimeOpt(int n) {

		if (n <= 1) return false;
		if (n == 2) return true;
		if (n % 2 == 0) return false;
		int m = (int) Math.sqrt(n);

		for (int i = 3; i <= m; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.primes_activity);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id
				.coordinatorLayout);
		edit1View = (EditText) findViewById(edit2);
		code2View = (TextView) findViewById(code2);
		code2View.setText(CODE2TEXT);
		result = (TextView) findViewById(R.id.result);
	}

	public void onGeneratePrimeNumbersButtonClicked(View view) {
		Intent intent = new Intent(this, SievePrimesActivity.class);
		startActivity(intent);
	}

	public void onPrimeNumberOptButtonClicked(View view) {
		String primeNumberString = edit1View.getText().toString();
		try {
			int primeNumberInput = TextUtils.isDigitsOnly(primeNumberString) && !TextUtils.isEmpty(primeNumberString) ? Integer.parseInt(primeNumberString) : 0;
			boolean isPrime = isPrimeOpt(primeNumberInput);
			result.setText(isPrime ? "Prime Number" : "Not prime");
		} catch (Exception e) {
			result.setText("Too big number to calculate");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		result.setText("");
	}

}
