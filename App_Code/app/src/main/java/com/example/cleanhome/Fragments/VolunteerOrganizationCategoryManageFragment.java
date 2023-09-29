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

import com.example.cleanhome.Adapter.CompanyCleaningCategoryAdapter;
import com.example.cleanhome.Adapter.VolunteerOrganizationCategoryAdapter;
import com.example.cleanhome.Model.CompanyCleaningCategory;
import com.example.cleanhome.Model.VolunteerOrganizationCategory;
import com.example.cleanhome.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class VolunteerOrganizationCategoryManageFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    String organizationId;


    List<VolunteerOrganizationCategory> volunteerOrganizationCategoryList;
    RecyclerView recyclerview;
    VolunteerOrganizationCategoryAdapter volunteerOrganizationCategoryAdapter;

    public VolunteerOrganizationCategoryManageFragment(){
        //constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_organization_category_manage, container, false);


        recyclerview = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerview.setLayoutManager(layoutManager);

        volunteerOrganizationCategoryList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        organizationId = firebaseUser.getUid();

        loadVolunteerCategory();


        return view;
    }

    private void loadVolunteerCategory() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("VolunteerOrganizationCategory");
        Query query = reference.orderByChild("organization_id").equalTo(organizationId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                volunteerOrganizationCategoryList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    VolunteerOrganizationCategory volunteerOrganizationCategory = snapshot.getValue(VolunteerOrganizationCategory.class);

                    volunteerOrganizationCategoryList.add(volunteerOrganizationCategory);

                    volunteerOrganizationCategoryAdapter = new VolunteerOrganizationCategoryAdapter((Context) getActivity(), (ArrayList<VolunteerOrganizationCategory>) volunteerOrganizationCategoryList);
                    recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerview.setAdapter(volunteerOrganizationCategoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}