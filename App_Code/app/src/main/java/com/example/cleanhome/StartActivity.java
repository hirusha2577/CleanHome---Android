package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class StartActivity extends AppCompatActivity {

    private Button recruitingCompany, volunteer, volunteerOrganization, cleaningCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recruitingCompany = findViewById(R.id.recruitingCompany);
        volunteer = findViewById(R.id.volunteer);
        volunteerOrganization = findViewById(R.id.volunteerOrganization);
        cleaningCompany = findViewById(R.id.cleaningCompany);

        recruitingCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra("user","recruitingCompany");
                startActivity(intent);
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra("user","volunteer");
                startActivity(intent);
            }
        });

        volunteerOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra("user","volunteerOrganization");
                startActivity(intent);
            }
        });

        cleaningCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra("user","cleaningCompany");
                startActivity(intent);
            }
        });

    }
}