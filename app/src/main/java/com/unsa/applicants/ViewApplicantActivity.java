package com.unsa.applicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unsa.applicants.domain.Applicant;
import com.unsa.applicants.service.ApplicantService;

public class ViewApplicantActivity extends AppCompatActivity {

    private ApplicantService applicantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant);
        // Recovering Intent from Menu Activity
        Intent intent = getIntent();
        try {
            applicantService = (ApplicantService) intent.getExtras().getSerializable("applicantService");
        } catch (Exception e) {
            Log.d("View Applicants Activity", "Something wrong with Applicant Service!");
        }
        // Recover Widgets on View Activity
        Button searchButton = findViewById(R.id.searchButton);
        EditText documentInput = findViewById(R.id.documentInput);
        TextView documentView = findViewById(R.id.documentOutput);
        TextView nameView = findViewById(R.id.nameOutput);
        TextView lastnameView = findViewById(R.id.lastnameOutput);
        TextView birthdayView = findViewById(R.id.birthdayOutput);
        TextView schoolView = findViewById(R.id.schoolOutput);
        TextView careerView = findViewById(R.id.careerOutput);
        // Adding a Event Listener on Search Button
        searchButton.setOnClickListener(v -> {
            Log.d("View Applicant Activity", "Looking for an Applicant");
            if (!documentInput.getText().toString().isEmpty()) {
                String document = documentInput.getText().toString();
                Applicant applicant = this.applicantService.findApplicant(document);
                if (applicant != null) {
                    documentView.setText(applicant.getDocument());
                    nameView.setText(applicant.getName());
                    lastnameView.setText(applicant.getLastname());
                    birthdayView.setText(applicant.getBirthday());
                    schoolView.setText(applicant.getSchool());
                    careerView.setText(applicant.getCareer());
                } else {
                    Toast.makeText(getBaseContext(), "Applicant not found in database!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}