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
import com.example.cleanhome.Model.CleaningCategory;
import com.example.cleanhome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecruitingCompanyPickCategoryFragment extends Fragment {


    List<CleaningCategory> cleaningCategoryList;
    RecyclerView recyclerview;
    CleaningCategoryAdapter cleaningCategoryAdapter;

    public  RecruitingCompanyPickCategoryFragment(){
        //constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruiting_company_pick_category, container, false);


        recyclerview = view.findViewById(R.id.cleaning_category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerview.setLayoutManager(layoutManager);

        cleaningCategoryList = new ArrayList<>();

        loadAllCleaningCategory();

        return view;
    }

    private void loadAllCleaningCategory() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ChooseCategory");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cleaningCategoryList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    CleaningCategory cleaningCategory = snapshot.getValue(CleaningCategory.class);

                    cleaningCategoryList.add(cleaningCategory);

                    cleaningCategoryAdapter = new CleaningCategoryAdapter((Context) getActivity(), (ArrayList<CleaningCategory>) cleaningCategoryList, "recruitingCompany");
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