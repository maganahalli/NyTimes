package com.mac.nytimes.activities;

import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.nytimes.R;
import com.mac.nytimes.commons.AppConstants;
import com.mac.nytimes.models.Multimedia;
import com.mac.nytimes.models.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MultimediaActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView caption;
    private ImageView media;

    private CoordinatorLayout coordinatorLayout;
    private TextView copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        caption = (TextView) findViewById(R.id.caption);
        copy = (TextView) findViewById(R.id.copy);
        media = (ImageView) findViewById(R.id.image);

        setToolbar();


        setData();
    }

    private void setData() {

        Bundle bundle = getIntent().getExtras();
        Result data = (Result) bundle.getSerializable(AppConstants.B_DATA);
        List<Multimedia> info = data.getMultimedia();
        int size = info.size();
        Multimedia mediaData = info.get(size - 1);
        caption.setText(mediaData.getCaption());
        copy.setText("Copyright \u00a9 "+mediaData.getCopyright());
        Picasso.with(this).load(mediaData.getUrl()).into(media, new Callback() {
            @Override
            public void onSuccess() {
                findViewById(R.id.progress).setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        TextView myTextView = (TextView) findViewById(R.id.app_title);
        myTextView.setTypeface(myTypeface);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        this.finish();
    }

}
