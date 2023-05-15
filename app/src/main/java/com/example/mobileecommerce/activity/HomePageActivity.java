package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.NavigationRecycleAdapter;
import com.example.mobileecommerce.api.LoginAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.fragment.HomeFragment;
import com.example.mobileecommerce.model.EShoppingModelClass;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.retrofit.RetrofitProvince;
import com.example.mobileecommerce.service.JwtService;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawer;
    private ArrayList<EShoppingModelClass> eShoppingModelClasses;
    LinearLayout fregmentlayout;
    private NavigationRecycleAdapter mAdapter;
    NavigationView navigationView;
    private RecyclerView recyclerView2;

    private String[] title2 = {"Home", "Cart", "My Orders", "Brands", "Offers", "My Account"};

    private int[] image2 = {R.drawable.home_, R.drawable.cart_, R.drawable.order, R.drawable.category, R.drawable.offer, R.drawable.profile_};
    private Toolbar toolbar;
    SharedPreferencesManager pres;
    ImageView ecart;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        pres = SharedPreferencesManager
                .getInstance(getSharedPreferences("jwt", MODE_PRIVATE));
//        checkJWT();
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);
        getWindow().setSoftInputMode(3);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setToolbar();
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview2);
        this.eShoppingModelClasses = new ArrayList<>();
        for (int i = 0; i < this.title2.length; i++) {
            this.eShoppingModelClasses.add(new EShoppingModelClass(this.title2[i], this.image2[i]));
        }
        this.mAdapter = new NavigationRecycleAdapter(this, this.eShoppingModelClasses);
        this.recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView2.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView2.setAdapter(this.mAdapter);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawer, this.toolbar, R.string.openDrawer, R.string.closeDrawer) { // from class: com.ecommerce.template.activity.HomePageActivity.1
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };
        this.actionBarDrawerToggle = actionBarDrawerToggle;
        this.drawer.setDrawerListener(actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
        this.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        invalidateOptionsMenu();
        this.fregmentlayout = (LinearLayout) findViewById(R.id.fregmentlayout);
        this.ecart = findViewById(R.id.iv_ecart);
        this.ecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCart();
            }
        });
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fregmentlayout, homeFragment, "Home Fragment");
        beginTransaction.commit();
    }

    private void gotoCart() {
        Intent intent = new Intent(this, MyCartActivity.class);
        startActivity(intent);
    }

    private void checkJWT() {
        try {
            String token = pres.getJWT();
            if (token != null) {
                if (!JwtService.validateTokenLogin(token)) {
                    gotoLogin();
                }
            }else{
                gotoLogin();
            }
        }catch (Exception e){
            Log.e("Lỗi",e.getMessage());
        }

    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
        supportActionBar.setTitle("");
        this.toolbar.findViewById(R.id.navigation_menu).setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HomePageActivity.this.drawer.isDrawerOpen(HomePageActivity.this.navigationView)) {
                    HomePageActivity.this.drawer.closeDrawer(HomePageActivity.this.navigationView);
                } else {
                    HomePageActivity.this.drawer.openDrawer(HomePageActivity.this.navigationView);
                }
            }
        });
//        this.toolbar.findViewById(R.id.iv_ecart).setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.activity.HomePageActivity.3
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, MyCartActivity.class));
//            }
//        });
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.actionBarDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.actionBarDrawerToggle.syncState();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.actionBarDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(Gravity.LEFT);
        } else {
            finish();
        }
    }

    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }
}
