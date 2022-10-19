package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText editTextName, editTextAge;
    public RadioGroup radioGroupGenders;
    public Spinner spinnerFrequency;
    public TextView textViewRating;
    public SeekBar seekBarRating;
    public CheckBox checkBoxAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        radioGroupGenders = (RadioGroup) findViewById(R.id.radioGroupGenders);
        spinnerFrequency = (Spinner) findViewById(R.id.spinnerFrequency);
        textViewRating = (TextView) findViewById(R.id.textViewRatingNum);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        checkBoxAgree = (CheckBox) findViewById(R.id.checkBoxAgree);

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

        Toast.makeText(
                getApplicationContext(),
                name + ", " + age,
                Toast.LENGTH_SHORT
        ).show();
        Toast.makeText(
                getApplicationContext(),
                getString(R.string.textGender) + gender,
                Toast.LENGTH_SHORT
        ).show();
        Toast.makeText(
                getApplicationContext(),
                getString(R.string.textToastFrequency) + frequency,
                Toast.LENGTH_SHORT
        ).show();
        Toast.makeText(
                getApplicationContext(),
                getString(R.string.textToastRating) + rating,
                Toast.LENGTH_SHORT
        ).show();
        Toast.makeText(
                getApplicationContext(),
                getString(R.string.textCheckBoxStatus) + checkBoxStatus,
                Toast.LENGTH_SHORT
        ).show();
    }
}