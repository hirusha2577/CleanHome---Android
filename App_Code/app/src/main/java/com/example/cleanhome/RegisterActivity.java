package com.example.cleanhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private String user;
    private ImageView backButton;
    private EditText name, email, password1, password2;
    private TextView moveLogin;
    private Button register;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        user = intent.getStringExtra("user");

        backButton = findViewById(R.id.backButton);
        moveLogin = findViewById(R.id.moveLogin);
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        moveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name = name.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password1 = password1.getText().toString();
                String txt_password2= password2.getText().toString();

                if (TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password1) || TextUtils.isEmpty(txt_password2)) {
                    Toast.makeText(RegisterActivity.this, "All filed are required", Toast.LENGTH_SHORT).show();
                } else if (!txt_password1.equals(txt_password2)) {
                    Toast.makeText(RegisterActivity.this, "Password not mach", Toast.LENGTH_SHORT).show();
                }else{
                    Register(txt_name, txt_email, txt_password1);
                }
            }
        });
    }

    private void Register(final String name,final String email,final String password1) {
        auth.createUserWithEmailAndPassword(email,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            String userUid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("User").child(userUid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userUid);
                            hashMap.put("name",name);
                            hashMap.put("email",email);
                            hashMap.put("contactNumber", "null");
                            hashMap.put("about", "null");
                            hashMap.put("address", "null");
                            hashMap.put("status", "0");
                            hashMap.put("P_imageURL", "default");
                            hashMap.put("type", user);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Register success...", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("user",user);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(RegisterActivity.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}