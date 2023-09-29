package com.example.cleanhome.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanhome.Model.CleaningCompany;
import com.example.cleanhome.R;
import com.example.cleanhome.RecruitingCompanyRequestFormActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class CleaningCompanyAdapter extends RecyclerView.Adapter<CleaningCompanyAdapter.CleaningCompanyViewholder> {
    Context context;
    ArrayList<CleaningCompany> list;


    public CleaningCompanyAdapter(Context context, ArrayList<CleaningCompany> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CleaningCompanyViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cleaning_company, parent, false);
        return new CleaningCompanyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CleaningCompanyViewholder holder, int position) {

        CleaningCompany cleaningCompany = list.get(position);

        String id = cleaningCompany.getId();
        String name = cleaningCompany.getName();
        String district = cleaningCompany.getDistrict();
        String count = cleaningCompany.getCount();

        holder.name.setText(name);
        holder.district.setText(district);
        holder.count.setText(count);


        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecruitingCompanyRequestFormActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class CleaningCompanyViewholder extends RecyclerView.ViewHolder {

        CardView itemContainer;
        TextView name, district, count;

        public CleaningCompanyViewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            itemContainer = itemView.findViewById(R.id.itemContainer);
            district = itemView.findViewById(R.id.district);
            count = itemView.findViewById(R.id.count);
        }
    }





}