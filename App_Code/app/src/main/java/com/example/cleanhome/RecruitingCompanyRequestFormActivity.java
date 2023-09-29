package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecruitingCompanyRequestFormActivity extends AppCompatActivity {

    TextView titleText;
    Button submit;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiting_company_request_form);

        titleText = findViewById(R.id.titleText);
        submit = findViewById(R.id.submit);
        backButton = findViewById(R.id.backButton);

        titleText.setText("Request Form");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecruitingCompanyRequestFormActivity.this, "Request success", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}