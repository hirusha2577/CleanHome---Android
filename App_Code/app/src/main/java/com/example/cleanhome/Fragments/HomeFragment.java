package com.example.cleanhome.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.cleanhome.CleaningCompanyProfileEdit;
import com.example.cleanhome.PostPublishActivity;
import com.example.cleanhome.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {


    private CardView post_add1;
    private LinearLayout post_add2;

    public  HomeFragment(){
        //constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        post_add1 = view.findViewById(R.id.post_add1);
        post_add2 = view.findViewById(R.id.post_add2);

        post_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostPublishActivity.class);
                startActivity(intent);
            }
        });

        post_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostPublishActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}