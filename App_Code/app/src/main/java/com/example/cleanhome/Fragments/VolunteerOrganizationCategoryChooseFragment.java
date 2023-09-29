package com.example.cleanhome.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanhome.Adapter.CleaningCategoryAdapter;
import com.example.cleanhome.Adapter.VolunteerCategoryAdapter;
import com.example.cleanhome.Model.CleaningCategory;
import com.example.cleanhome.Model.VolunteerCategory;
import com.example.cleanhome.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class VolunteerOrganizationCategoryChooseFragment extends Fragment {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    List<VolunteerCategory> volunteerCategoryList;
    RecyclerView recyclerview;
    VolunteerCategoryAdapter volunteerCategoryAdapter;


    public VolunteerOrganizationCategoryChooseFragment(){
        //constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_organization_category_choose, container, false);

        recyclerview = view.findViewById(R.id.volunteer_category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerview.setLayoutManager(layoutManager);

        volunteerCategoryList = new ArrayList<>();

        loadAllVolunteerCategory();


        return view;
    }

    private void loadAllVolunteerCategory() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("VolunteerCategory");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                volunteerCategoryList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    VolunteerCategory volunteerCategory = snapshot.getValue(VolunteerCategory.class);

                    volunteerCategoryList.add(volunteerCategory);

                    volunteerCategoryAdapter = new VolunteerCategoryAdapter((Context) getActivity(), (ArrayList<VolunteerCategory>) volunteerCategoryList, "volunteerOrganization");
                    recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerview.setAdapter(volunteerCategoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}