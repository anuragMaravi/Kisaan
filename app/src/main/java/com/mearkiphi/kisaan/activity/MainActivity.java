package com.mearkiphi.kisaan.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.fragments.CardsFragment;
import com.mearkiphi.kisaan.fragments.SellFragment;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.MessageResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

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
                case R.id.navigation_dashboard:
                    toolbar_title =(TextView) findViewById(R.id.toolbar_title);
                    toolbar_title.setText("BUY");
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
    private void handleError(ResponseBody error) {
        try {
            MessageResponse messageResponse = new Gson().fromJson(error.string(), MessageResponse.class);
            showErrorAlert(messageResponse.getMessage(),null);
            if (messageResponse.getMessage().contentEquals("invalid authorization token"))
                completeUserLogout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.signOut:
                AlertDialog.Builder signOutAlert = new AlertDialog.Builder(this);
                signOutAlert.setTitle("Sign Out");
                signOutAlert.setMessage("Are you sure you want to sign out?");
                signOutAlert.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                signOutAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Hasura.auth.logout().enqueue(new Callback<MessageResponse>() {
                            @Override
                            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                if (response.isSuccessful()) {
                                    completeUserLogout();
                                } else {
                                     handleError(response.errorBody());
                                }
                            }

                            @Override
                            public void onFailure(Call<MessageResponse> call, Throwable t) {
//                                hideProgressIndicator();
//                                showErrorAlert("Please ensure that you have a working internet connection",null);
                            }
                        });
                    }
                });
                return true;
        }
        return false;
    }

    private void completeUserLogout() {
        Hasura.clearSession();
        AuthenticationActivity.startActivity(MainActivity.this);
    }

}
