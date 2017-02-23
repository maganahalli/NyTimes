package com.mac.nytimes.activities;

import java.util.Arrays;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mac.nytimes.R;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.mac.nytimes.R.id.edit2;

public class SievePrimesActivity extends AppCompatActivity {

	private static final String TAG = SievePrimesActivity.class.getSimpleName();
	private CoordinatorLayout coordinatorLayout;
	EditText editView;
	TextView result;
	Snackbar snackbar;

	protected String getPrimesString() {
		String primeNumberString = editView.getText().toString();
		StringBuffer buffer = new StringBuffer();
		try {
			int primeNumberInput = TextUtils.isDigitsOnly(primeNumberString) && !TextUtils.isEmpty(primeNumberString) ? Integer.parseInt(primeNumberString) : 0;

			boolean[] primesList = getSieve(primeNumberInput);
			for (int i = 0; i < primesList.length; i++) {
				if (primesList[i]) {
					buffer.append("" + i + "").append(" ");
				}
			}
			return buffer.toString();
		} catch (Exception e) {
			showMessage("Too big number to calculate");
			return "Error";
		}
	}

	protected boolean[] getSieve(int n) {
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		int m = (int) Math.sqrt(n);

		for (int i = 2; i <= m; i++)
			if (prime[i])
				for (int k = i * i; k <= n; k += i)
					prime[k] = false;

		return prime;
	}

	private SingleObserver<String> getSingleObserver() {
		return new SingleObserver<String>() {
			@Override
			public void onError(Throwable e) {
				result.append(" onError : " + e.getMessage());
				Log.d(TAG, " onError : " + e.getMessage());
			}

			@Override
			public void onSubscribe(Disposable d) {
				Log.d(TAG, " onSubscribe : " + d.isDisposed());
			}

			@Override
			public void onSuccess(String value) {
				result.setText(" Primes : " + value);
				Log.d(TAG, " primes : " + value);
			}
		};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sieve_primes_activity);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id
				.coordinatorLayout);
		editView = (EditText) findViewById(edit2);
		result = (TextView) findViewById(R.id.result);
	}

	public void onPrimeNumberGenerateButtonClicked(View view) {

		Single.just(getPrimesString())
				.subscribe(getSingleObserver());
	}

	protected void showMessage(String message) {
		if (snackbar != null) {
			snackbar.dismiss();
			snackbar = null;
		}
		snackbar = Snackbar
				.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);

		View sbView = snackbar.getView();
		TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
		textView.setTextColor(Color.YELLOW);
		snackbar.setActionTextColor(Color.RED);
		snackbar.show();
	}
}
