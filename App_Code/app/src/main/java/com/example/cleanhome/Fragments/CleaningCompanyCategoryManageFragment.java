package com.example.cleanhome.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanhome.Adapter.CleaningCategoryAdapter;
import com.example.cleanhome.Adapter.CompanyCleaningCategoryAdapter;
import com.example.cleanhome.Model.CleaningCategory;
import com.example.cleanhome.Model.CompanyCleaningCategory;
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


public class CleaningCompanyCategoryManageFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    String companyId;


    List<CompanyCleaningCategory> cleaningCategoryList;
    RecyclerView recyclerview;
    CompanyCleaningCategoryAdapter cleaningCategoryAdapter;

    public CleaningCompanyCategoryManageFragment() {
        //constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_cleaning_company_category_manage, container,false);

        recyclerview = view.findViewById(R.id.cleaning_category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerview.setLayoutManager(layoutManager);

        cleaningCategoryList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        companyId = firebaseUser.getUid();

        loadAllCleaningCategory();

        return view;
    }

    private void loadAllCleaningCategory() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CompanyCleaningCategory");
        Query query = reference.orderByChild("company_id").equalTo(companyId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cleaningCategoryList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    CompanyCleaningCategory cleaningCategory = snapshot.getValue(CompanyCleaningCategory.class);

                    cleaningCategoryList.add(cleaningCategory);

                    cleaningCategoryAdapter = new CompanyCleaningCategoryAdapter((Context) getActivity(), (ArrayList<CompanyCleaningCategory>) cleaningCategoryList);
                    recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerview.setAdapter(cleaningCategoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}