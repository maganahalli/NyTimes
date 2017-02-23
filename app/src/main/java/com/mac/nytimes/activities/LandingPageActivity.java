package com.mac.nytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mac.nytimes.R;
import com.mac.nytimes.pojo.AceBST;
import com.mac.nytimes.pojo.AceStringManager;
import com.mac.nytimes.pojo.MyQueue;

public class LandingPageActivity extends AppCompatActivity {

	private TextView btreeTextLabelView;
	private TextView queueTextLabelView;

	protected void buildBTree(AceBST<String, Integer> st) {
		String key = "16";
		st.put(key, Integer.valueOf(16));

		String key1 = "25";
		st.put(key1, Integer.valueOf(25));

		String key2 = "27";
		st.put(key2, Integer.valueOf(27));

		String key3 = "24";
		st.put(key3, Integer.valueOf(24));

		String key4 = "13";
		st.put(key4, Integer.valueOf(13));

		String key5 = "8";
		st.put(key5, Integer.valueOf(8));

		String key6 = "12";
		st.put(key6, Integer.valueOf(12));

		String key7 = "14";
		st.put(key7, Integer.valueOf(14));

	}

	public void onBasesButtonClicked(View view) {
		Intent intent = new Intent(this, BasesActivity.class);
		startActivity(intent);
	}

	public void onBtreeButtonClicked(View view) {

		AceBST<String, Integer> st = new AceBST<String, Integer>();
		buildBTree(st);
		StringBuilder builder = new StringBuilder();
		builder.append(" Height of Tree " + st.height()).append("\n");
		st.bfsTraverse();

		st.bfsTraverseWithSprialPattern();

		AceStringManager manager = new AceStringManager();
		String pString = "abcdedcba";
		builder.append("palindrome text input : ").append(pString).append("\n");
		if (manager.isPalinDrome(pString)) {
			builder.append("is Palindrome").append("\n");
		} else {
			builder.append("is NOT Palindrome").append("\n");
		}

		btreeTextLabelView.setText(builder.toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing_page_activity);
		queueTextLabelView = (TextView) findViewById(R.id.queueTextLabel);
		btreeTextLabelView = (TextView) findViewById(R.id.btreeTextLabel);
	}

	public void onDataStructuresButtonClicked(View view) {
		Intent intent = new Intent(this, DataStructuresActivity.class);
		startActivity(intent);
	}

	public void onFractionAndNumberButtonClicked(View view) {
		Intent intent = new Intent(this, FractionsActivity.class);
		startActivity(intent);
	}

	public void onGcdButtonClicked(View view) {
		Intent intent = new Intent(this, GcdActivity.class);
		startActivity(intent);
	}

	public void onGeometryButtonClicked(View view) {
		Intent intent = new Intent(this, GeometryActivity.class);
		startActivity(intent);
	}

	public void onPrimesButtonClicked(View view) {
		Intent intent = new Intent(this, PrimesActivity.class);
		startActivity(intent);
	}

	public void onQueueButtonClicked(View view) {

		MyQueue<Integer> queue = new MyQueue<>();
		StringBuilder builder = new StringBuilder();
		// enqueue integers 1..3
		for (int i = 1; i <= 5; i++) {
			queue.enqueue(i + 5);
		}

		// execute 2 dequeue operations
		for (int i = 0; i < 2; i++) {
			builder.append("Dequeued: " + queue.dequeue()).append("\n");
		}

		// enqueue integers 4..5
		for (int i = 6; i <= 11; i++) {
			queue.enqueue(i + 5);
		}

		// dequeue the rest
		while (!queue.isEmpty()) {
			builder.append("Dequeued: " + queue.dequeue()).append("\n");
		}

		queueTextLabelView.setText(builder.toString());

	}

	@Override
	protected void onResume() {
		super.onResume();
		queueTextLabelView.setText("");
		btreeTextLabelView.setText("");
	}
}
