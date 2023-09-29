package com.example.cleanhome;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cleanhome.Fragments.CleaningCompanyCategoryChooseFragment;
import com.example.cleanhome.Fragments.CleaningCompanyCategoryManageFragment;
import com.example.cleanhome.Fragments.CleaningCompanyManageRequestFragment;
import com.example.cleanhome.Fragments.HomeFragment;
import com.example.cleanhome.Fragments.ProfileFragment;
import com.example.cleanhome.Fragments.RecruitingCompanyPickCategoryFragment;
import com.example.cleanhome.Fragments.VolunteerOrganizationCategoryChooseFragment;
import com.example.cleanhome.Fragments.VolunteerOrganizationCategoryManageFragment;
import com.example.cleanhome.Fragments.VolunteerOrganizationManageRequestFragment;
import com.example.cleanhome.Fragments.VolunteerOrganizationPublishEventFragment;
import com.example.cleanhome.Fragments.VolunteerPickCategoryFragment;
import com.example.cleanhome.Fragments.VolunteerRequestHistoryFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String user;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        user = intent.getStringExtra("user");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);

        if(user.equals("cleaningCompany")){
            Menu menu =navigationView.getMenu();
            MenuItem navChooseCategory = menu.findItem(R.id.nav_cleaning_company_choose_category);
            navChooseCategory.setVisible(true);
            MenuItem navManageCategory = menu.findItem(R.id.nav_cleaning_company_manage_category);
            navManageCategory.setVisible(true);
            MenuItem navManageRequest = menu.findItem(R.id.nav_cleaning_company_manage_request);
            navManageRequest.setVisible(true);
        }else if(user.equals("recruitingCompany")){
            Menu menu =navigationView.getMenu();
            MenuItem navPickCategory = menu.findItem(R.id.nav_recruiting_company_pick_category);
            navPickCategory.setVisible(true);
        }else if(user.equals("volunteerOrganization")){
            Menu menu =navigationView.getMenu();
            MenuItem navPickCategory = menu.findItem(R.id.nav_volunteer_organization_choose_category);
            navPickCategory.setVisible(true);
            MenuItem navManageCategory = menu.findItem(R.id.nav_volunteer_organization_manage_category);
            navManageCategory.setVisible(true);
            MenuItem navPublishEvent = menu.findItem(R.id.nav_volunteer_organization_publish_event);
            navPublishEvent.setVisible(true);
            MenuItem navManageRequest = menu.findItem(R.id.nav_volunteer_organization_manage_request);
            navManageRequest.setVisible(true);
        }else{
            Menu menu =navigationView.getMenu();
            MenuItem navPickCategory = menu.findItem(R.id.nav_volunteer_pick_category);
            navPickCategory.setVisible(true);
            MenuItem navRequestHistory = menu.findItem(R.id.nav_volunteer_RequestHistory);
            navRequestHistory.setVisible(true);
        }

    }


    private void displaySelectedScreen(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.nav_home:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
                toolbar.setBackgroundColor(getResources().getColor(R.color.baseColor));
                fragment = new ProfileFragment();
                break;
            case R.id.nav_cleaning_company_choose_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new CleaningCompanyCategoryChooseFragment();
                break;
            case R.id.nav_cleaning_company_manage_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new CleaningCompanyCategoryManageFragment();
                break;
            case R.id.nav_cleaning_company_manage_request:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new CleaningCompanyManageRequestFragment();
                break;
            case R.id.nav_volunteer_organization_choose_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerOrganizationCategoryChooseFragment();
                break;
            case R.id.nav_recruiting_company_pick_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new RecruitingCompanyPickCategoryFragment();
                break;
            case R.id.nav_volunteer_organization_manage_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerOrganizationCategoryManageFragment();
                break;
            case R.id.nav_volunteer_organization_publish_event:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerOrganizationPublishEventFragment();
                break;
            case R.id.nav_volunteer_organization_manage_request:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerOrganizationManageRequestFragment();
                break;
            case R.id.nav_volunteer_pick_category:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerPickCategoryFragment();
                break;
            case R.id.nav_volunteer_RequestHistory:
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                fragment = new VolunteerRequestHistoryFragment();
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                break;

        }
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}