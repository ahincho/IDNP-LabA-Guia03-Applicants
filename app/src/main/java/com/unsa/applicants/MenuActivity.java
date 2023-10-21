package com.unsa.applicants;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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
        // Using Intent and RegisterForActivityResult to Launch Add Applicant Activity
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Log.d("Menu Activity", "Go to Add Applicant Activity");
            addLauncher.launch(new Intent(getApplicationContext(), AddApplicantActivity.class));
        });
    }

    ActivityResultLauncher<Intent> addLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Bundle extras = null;
                        if (result.getData() != null) {
                            extras = result.getData().getExtras();
                        }
                        Applicant applicant = null;
                        if (extras != null) {
                            applicant = (Applicant) extras.get("applicant");
                        }
                        applicantService.saveApplicant(applicant);
                        Toast.makeText(getBaseContext(), "Applicant was recorded on database!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Something went wrong ...", Toast.LENGTH_LONG).show();
                    }
                }
            });

}