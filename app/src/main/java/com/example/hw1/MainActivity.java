package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public EditText editTextName, editTextAge;
    public RadioGroup radioGroupGenders;
    public Spinner spinnerFrequency;
    public TextView textViewBusStopName, textViewRating;
    public SeekBar seekBarRating;
    public CheckBox checkBoxAgree;

    private String[] busStopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mapsActivityIntent = getIntent();
        busStopInfo = mapsActivityIntent.getStringExtra("busStopInfo").split(",");
        String stopName = getString(R.string.textViewBusStopName) + " " + busStopInfo[0];

        textViewBusStopName = (TextView) findViewById(R.id.textViewBusStopName);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        radioGroupGenders = (RadioGroup) findViewById(R.id.radioGroupGenders);
        spinnerFrequency = (Spinner) findViewById(R.id.spinnerFrequency);
        textViewRating = (TextView) findViewById(R.id.textViewRatingNum);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        checkBoxAgree = (CheckBox) findViewById(R.id.checkBoxAgree);

        textViewBusStopName.setText(stopName);
        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewRating.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClickSubmit(View view) {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String gender = radioGroupGenders.getCheckedRadioButtonId() == R.id.radioButtonMale ? getString(R.string.textRadioMale)
                : radioGroupGenders.getCheckedRadioButtonId() == R.id.radioButtonFemale ? getString(R.string.textRadioFemale)
                : radioGroupGenders.getCheckedRadioButtonId() == R.id.radioButtonElse ? getString(R.string.textRadioElse)
                : "Gender Not Chosen";
        String frequency = spinnerFrequency.getSelectedItem().toString();
        String rating = textViewRating.getText().toString();
        boolean checkBoxStatus = checkBoxAgree.isChecked();

        if (checkBoxStatus) {
            if (name.equals("") || age.equals("") || gender.equals("") || frequency.equals("") || rating.equals("")) {
                Toast.makeText(this, "Missing Information!", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> survey = new HashMap<>();
            survey.put("name", name);
            survey.put("age", Integer.valueOf(age));
            survey.put("gender", gender);
            survey.put("frequency", frequency);
            survey.put("rating", Integer.valueOf(rating));
            survey.put("stopName", busStopInfo[0]);
            survey.put("stopPlusCode", busStopInfo[1]);

            db.collection("busStopSurveys")
                .add(survey)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Survey Submitted!", Toast.LENGTH_SHORT).show();
                        Log.d("Submitted Data", "Id: " + documentReference.getId());
                        Log.d("Content", "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Rating: " + rating);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Survey wasn't submitted", Toast.LENGTH_SHORT).show();
                        Log.w("Submission Error", e.getLocalizedMessage());
                    }
                });
        } else {
            Toast.makeText(this, "You need to agree to submit!", Toast.LENGTH_SHORT).show();
        }
    }
}