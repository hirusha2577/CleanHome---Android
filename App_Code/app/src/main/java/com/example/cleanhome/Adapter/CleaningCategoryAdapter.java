package com.example.cleanhome.Adapter;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanhome.LoginActivity;
import com.example.cleanhome.Model.CleaningCategory;
import com.example.cleanhome.R;
import com.example.cleanhome.RecruitingCompanyCleaningCompanyListActivity;
import com.example.cleanhome.StartActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class CleaningCategoryAdapter extends RecyclerView.Adapter<CleaningCategoryAdapter.CleaningCategoryViewholder> {
    Context context;
    ArrayList<CleaningCategory> list;

    ProgressDialog pd;
    String type;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public CleaningCategoryAdapter(Context context, ArrayList<CleaningCategory> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public CleaningCategoryViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CleaningCategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CleaningCategoryViewholder holder, int position) {

        CleaningCategory cleaningCategory = list.get(position);

        int id = cleaningCategory.getId();
        String name = cleaningCategory.getName();
        String image = cleaningCategory.getImage();

        holder.name.setText(name);
        if (image.equals("default")) {
            holder.image.setImageResource(R.mipmap.ic_launcher);
        } else {
            try {
                Picasso.get().load(image).into(holder.image);
            } catch (Exception e) {

            }
        }
        try {
            Picasso.get().load(image).placeholder(R.mipmap.ic_launcher).into(holder.image);
        } catch (Exception e) {

        }

        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("cleaningCompany")) {
                    ShowDialogBox(id, name, image);
                }else{
                    Intent intent = new Intent(context, RecruitingCompanyCleaningCompanyListActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class CleaningCategoryViewholder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        LinearLayout itemContainer;

        public CleaningCategoryViewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }
    }

    private void ShowDialogBox(int id, String name, String image) {
        String[] options = {"Yes", "No"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure to choose");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Log.d(TAG, "id  ::::::::::::::::::::: " + id);
                    Log.d(TAG, "Name  ::::::::::::::::::::: " + name);
                    Log.d(TAG, "image  ::::::::::::::::::::: " + image);

                    firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    String companyId = firebaseUser.getUid();

                    final String timestamp = String.valueOf(System.currentTimeMillis());

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", timestamp);
                    hashMap.put("company_id", companyId);
                    hashMap.put("category_id", String.valueOf(id));
                    hashMap.put("category_name", name);
                    hashMap.put("category_image", image);

                    databaseReference = FirebaseDatabase.getInstance().getReference("CompanyCleaningCategory");
                    databaseReference.child(timestamp).setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(context, "success", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
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