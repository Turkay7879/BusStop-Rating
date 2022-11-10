package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FormDetailsActivity extends AppCompatActivity {

    public TextView textViewBusStopName, textViewName, textViewAge, textViewGender, textViewFreq, textViewRating, textViewAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_details);

        Intent detailsFromMainIntent = getIntent();

        textViewBusStopName = (TextView) findViewById(R.id.textViewBusStopDetailsActivity);
        textViewName = (TextView) findViewById(R.id.textViewNameDetailsActivity);
        textViewAge = (TextView) findViewById(R.id.textViewAgeDetailsActivity);
        textViewGender = (TextView) findViewById(R.id.textViewGenderDetailsActivity);
        textViewFreq = (TextView) findViewById(R.id.textViewFrequencyDetailsActivity);
        textViewRating = (TextView) findViewById(R.id.textViewRatingDetailsActivity);
        textViewAgree = (TextView) findViewById(R.id.textViewFormAgreedDetailsActivity);

        textViewBusStopName.setText(detailsFromMainIntent.getStringExtra("busStopName"));
        textViewName.setText(getString(R.string.textName) + detailsFromMainIntent.getStringExtra("name"));
        textViewAge.setText(getString(R.string.textAge) + detailsFromMainIntent.getStringExtra("age"));
        textViewGender.setText(getString(R.string.textGender) + detailsFromMainIntent.getStringExtra("gender"));
        textViewFreq.setText(getString(R.string.textToastFrequency) + detailsFromMainIntent.getStringExtra("frequency"));
        textViewRating.setText(getString(R.string.textToastRating) + detailsFromMainIntent.getStringExtra("rating"));
        textViewAgree.setText(getString(R.string.textCheckBoxStatus) + detailsFromMainIntent.getBooleanExtra("checkBoxStatus", false));
    }

    public void onClickBackButton(View view) {
        finish();
    }
}