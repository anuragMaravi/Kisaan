package com.mearkiphi.kisaan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.fragments.CardsFragment;
import com.mearkiphi.kisaan.fragments.SellFragment;


public class MainActivity extends AppCompatActivity {

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity,MainActivity.class));
        startingActivity.finish();
    }



    private Toolbar toolbar;
    private TextView toolbar_title;

    BottomNavigationView navigation;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Fragment fragment,fragme;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        fragme = new CardsFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragme).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar_title =(TextView) findViewById(R.id.toolbar_title);
                    toolbar_title.setText("MOVIES");
                    fragment = new CardsFragment();
                    break;
                case R.id.navigation_notifications:
                    toolbar_title =(TextView) findViewById(R.id.toolbar_title);
                    toolbar_title.setText("SELL");
                    fragment = new SellFragment();

                    break;
                default:
                    fragment = new CardsFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            Log.i(TAG, "onNavigationItemSelected: " + getFragmentManager().getBackStackEntryCount());
            transaction.replace(R.id.container, fragment).commit();
            return true;
        }

    };

}
