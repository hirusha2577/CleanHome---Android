package com.example.cleanhome;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cleanhome.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CleaningCompanyProfileEdit extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String companyId;

    private User user = new User();
    private User newUser = new User();

    private TextInputEditText aboutEditText, nameEditText, emailEditText, addressEditText,
            contactNoEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_company_profile_edit);

        aboutEditText = findViewById(R.id.profileAbout);
        nameEditText = findViewById(R.id.profileName);
        emailEditText = findViewById(R.id.profileEmail);
        addressEditText = findViewById(R.id.profileAddress);
        contactNoEditText = findViewById(R.id.profileContactNo);

        saveButton = findViewById(R.id.saveButton);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        companyId = firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("User/" + companyId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
//                Toast.makeText(CleaningCompanyProfileEdit.this, user.getEmail(), Toast.LENGTH_SHORT).show();
                if (user != null) {
                    if (!user.getName().equals("null")) {
                        nameEditText.setText(user.getName());
                    }
                    if (!user.getAbout().equals("null")) {
                        aboutEditText.setText(user.getAbout());
                    }
                    if (!user.getEmail().equals("null")) {
                        emailEditText.setText(user.getEmail());
                    }
                    if (!user.getContactNumber().equals("null")) {
                        contactNoEditText.setText(user.getContactNumber());
                    }
                    if (!user.getAddress().equals("null")) {
                        addressEditText.setText(user.getAddress());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CleaningCompanyProfileEdit.this, "Something went wrong. Please log again", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String name = nameEditText.getText().toString();
                 String about = aboutEditText.getText().toString();
                 String email = emailEditText.getText().toString();
                 String address = addressEditText.getText().toString();
                 String contactNo = contactNoEditText.getText().toString();
                 String userUid = user.getId();
                 String imageUrl = user.getP_imageURL();
                 String status = user.getStatus();
                 String type = user.getType();

                 User newUser = new User(imageUrl, about, address, contactNo, email, userUid, name, status, type);

//                 databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userUid);
//                 databaseReference.setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
//                     @Override
//                     public void onComplete(@NonNull Task<Void> task) {
//                         Toast.makeText(CleaningCompanyProfileEdit.this, "Register success...", Toast.LENGTH_SHORT).show();
//                         finish();
//                     }
//                 });

                ShowDialogBox(userUid, newUser);


            }
        });
    }

    private void ShowDialogBox(String userUid, User newUser) {
        String[] options = {"Yes", "No"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure to edit profile?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userUid);
                    databaseReference.setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(CleaningCompanyProfileEdit.this, "User profile updated successfully", Toast.LENGTH_SHORT).show();
//                            finish();
                        }
                    });
                }
                if (which == 1) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }
}