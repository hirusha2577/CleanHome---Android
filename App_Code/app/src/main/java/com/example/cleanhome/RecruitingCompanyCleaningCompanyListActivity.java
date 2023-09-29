package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecruitingCompanyCleaningCompanyListActivity extends AppCompatActivity {

    String id, name;
    TextView titleText;
    ImageView backButton;
    CardView itemContainer, itemContainer1, itemContainer2,itemContainer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiting_company_cleaning_company_list);

        titleText = findViewById(R.id.titleText);
        backButton = findViewById(R.id.backButton);

        itemContainer = findViewById(R.id.itemContainer);
        itemContainer1 = findViewById(R.id.itemContainer1);
        itemContainer2 = findViewById(R.id.itemContainer2);
        itemContainer3 = findViewById(R.id.itemContainer3);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");

        titleText.setText(name);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitingCompanyCleaningCompanyListActivity.this, RecruitingCompanyRequestFormActivity.class);
                startActivity(intent);

            }
        });
        itemContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitingCompanyCleaningCompanyListActivity.this, RecruitingCompanyRequestFormActivity.class);
                startActivity(intent);
            }
        });
        itemContainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitingCompanyCleaningCompanyListActivity.this, RecruitingCompanyRequestFormActivity.class);
                startActivity(intent);
            }
        });
        itemContainer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitingCompanyCleaningCompanyListActivity.this, RecruitingCompanyRequestFormActivity.class);
                startActivity(intent);
            }
        });

    }
}