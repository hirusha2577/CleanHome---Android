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

import com.example.cleanhome.Model.CleaningCategory;
import com.example.cleanhome.Model.CompanyCleaningCategory;
import com.example.cleanhome.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class CompanyCleaningCategoryAdapter extends RecyclerView.Adapter<CompanyCleaningCategoryAdapter.CleaningCategoryViewholder> {
    Context context;
    ArrayList<CompanyCleaningCategory> list;

    private DatabaseReference databaseReference;

    public CompanyCleaningCategoryAdapter(Context context, ArrayList<CompanyCleaningCategory> list) {
        this.context = context;
        this.list = list;
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

        CompanyCleaningCategory cleaningCategory = list.get(position);

        String id = cleaningCategory.getId();
        String company_id = cleaningCategory.getCompany_id();
        String category_id = cleaningCategory.getCategory_id();
        String category_name= cleaningCategory.getCategory_name();
        String category_image = cleaningCategory.getCategory_image();

        holder.name.setText(category_name);
        if (category_image.equals("default")) {
            holder.image.setImageResource(R.mipmap.ic_launcher);
        } else {
            try {
                Picasso.get().load(category_image).into(holder.image);
            } catch (Exception e) {

            }
        }
        try {
            Picasso.get().load(category_image).placeholder(R.mipmap.ic_launcher).into(holder.image);
        } catch (Exception e) {

        }

        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogBox(id);
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

    private void ShowDialogBox(String id) {
        String[] options = {"Yes", "No"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure to remove category");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    removeCategory(id);
                }
                if (which == 1) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }

    private void removeCategory(final String post_id) {

        databaseReference = FirebaseDatabase.getInstance().getReference("CompanyCleaningCategory").child(post_id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    ds.getRef().removeValue();
                    Toast.makeText(context, "Remove success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(context, "Failed to remove", Toast.LENGTH_SHORT).show();
            }
        });
    }



}