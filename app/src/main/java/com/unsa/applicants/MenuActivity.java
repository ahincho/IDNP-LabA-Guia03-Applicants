package com.unsa.applicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.unsa.applicants.domain.Applicant;
import com.unsa.applicants.service.ApplicantService;
import com.unsa.applicants.service.ApplicantServiceMemory;

public class MenuActivity extends AppCompatActivity {

    private final ApplicantService applicantService = new ApplicantServiceMemory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Creating Applicants
        Applicant applicant1 = new Applicant("12345", "John", "Doe", "1990-01-15", "High School ABC", "Computer Science");
        Applicant applicant2 = new Applicant("67890", "Jane", "Smith", "1995-03-20", "High School ABC", "Engineering");
        Applicant applicant3 = new Applicant("54321", "Alice", "Johnson", "1988-11-05", "College ABC", "Business");
        applicantService.saveApplicant(applicant1);
        applicantService.saveApplicant(applicant2);
        applicantService.saveApplicant(applicant3);
        // Using Intent to Launch View Applicant Activity
        Button viewButton = findViewById(R.id.viewButton);
        viewButton.setOnClickListener(v -> {
            Log.d("Menu Activity", "Go to View Applicant Activity");
            Intent intentView = new Intent(getApplicationContext(), ViewApplicantActivity.class);
            intentView.putExtra("applicantService", this.applicantService);
            startActivity(intentView);
        });
    }

}