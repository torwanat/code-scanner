package com.example.codescanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private int modePosition = 0;
    private int codeWidth = 1024;
    private int codeHeight = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener("MODE_POSITION_CHANGED", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                modePosition = result.getInt("MODE_POSITION");
            }
        });
        getSupportFragmentManager().setFragmentResultListener("CODE_DIMENSIONS_CHANGED", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.getInt("CODE_WIDTH") != -1) codeWidth = result.getInt("CODE_WIDTH");
                if (result.getInt("CODE_HEIGHT") != -1) codeHeight = result.getInt("CODE_HEIGHT");
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId") //he he
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.scanner:
                        fragmentR(new ScanFragment());
                        break;
                    case R.id.bar_generator:
                        fragmentR(new GenerateBarFragment());
                        break;
                    case R.id.qr_generator:
                        fragmentR(new GenerateQRFragment());
                        break;
                    case R.id.settings:
                        fragmentR(new SettingsFragment());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void fragmentR(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putInt("MODE_POSITION", modePosition);
        bundle.putInt("CODE_WIDTH", codeWidth);
        bundle.putInt("CODE_HEIGHT", codeHeight);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}