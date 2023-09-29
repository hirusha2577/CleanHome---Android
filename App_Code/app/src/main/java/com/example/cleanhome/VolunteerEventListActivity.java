package com.example.cleanhome;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class VolunteerEventListActivity extends AppCompatActivity {

    String id, name;
    TextView titleText;
    ImageView backButton;
    CardView itemContainer, itemContainer1, itemContainer2,itemContainer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_event_list);

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
                ShowDialogBox();

            }
        });
        itemContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogBox();
            }
        });
        itemContainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogBox();
            }
        });
        itemContainer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogBox();
            }
        });

    }

    private void ShowDialogBox() {
        String[] options = {"Yes", "No"};
        AlertDialog.Builder builder = new AlertDialog.Builder(VolunteerEventListActivity.this);
        builder.setTitle("Are you sure join event");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Toast.makeText(VolunteerEventListActivity.this, "Request success", Toast.LENGTH_LONG).show();
                    finish();
                }if (which == 1) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }
}