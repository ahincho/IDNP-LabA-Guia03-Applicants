package com.unsa.applicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unsa.applicants.domain.Applicant;

public class AddApplicantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_applicant);
        // Recover Widgets in Add Applicant Activity
        Button addApplicantButton = findViewById(R.id.addApplicantButton);
        EditText documentText = findViewById(R.id.documentText);
        EditText nameText = findViewById(R.id.nameText);
        EditText lastnameText = findViewById(R.id.lastnameText);
        EditText birthdayText = findViewById(R.id.birthdayText);
        EditText schoolText = findViewById(R.id.schoolText);
        EditText careerText = findViewById(R.id.careerText);
        addApplicantButton.setOnClickListener(v -> {
            Log.d("Add Applicant Activity", "Trying to Save an Applicant");
            // Recover Strings and Texts from Widgets
            String document = documentText.getText().toString();
            String name = nameText.getText().toString();
            String lastname = lastnameText.getText().toString();
            String birthday = birthdayText.getText().toString();
            String school = schoolText.getText().toString();
            String career = careerText.getText().toString();
            // Validation of the Form
            if (isValidInput(document, name, lastname, birthday, school, career)) {
                Applicant newApplicant = new Applicant(document, name, lastname, birthday, school, career);
                Toast.makeText(this, "Applicant was recorded!", Toast.LENGTH_LONG).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("applicant", newApplicant);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private boolean isValidInput(String document, String name, String lastname, String birthday, String school, String career) {
        if (document.isEmpty() || name.isEmpty() || lastname.isEmpty() || birthday.isEmpty() || school.isEmpty() || career.isEmpty()) {
            Toast.makeText(getBaseContext(), "Please complete all form fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}