package com.mearkiphi.kisaan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.AuthRequest;
import com.mearkiphi.kisaan.models.AuthResponse;
import com.mearkiphi.kisaan.models.MessageResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationActivity extends BaseActivity implements View.OnClickListener {

    EditText username, password;
    Button signInButton, registerButton;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, AuthenticationActivity.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hasura.initialise(this);
        setContentView(R.layout.activity_authentication);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signInButton = (Button) findViewById(R.id.signInButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        signInButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        if (Hasura.getUserSessionId() != null) {
            MainActivity.startActivity(AuthenticationActivity.this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButton:
                handleLogin();
                break;
            case R.id.registerButton:
                handleRegistration();
                break;
        }
    }

    private void handleLogin() {
        showProgressIndicator();
        Hasura.auth.login(new AuthRequest(username.getText().toString(), password.getText().toString())).enqueue(new Callback<AuthResponse>() {

            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.i("log", "handleLogin: ");
                hideProgressIndicator();
                if (response.isSuccessful()) {
                    Hasura.setSession(response.body());
                    MainActivity.startActivity(AuthenticationActivity.this);
                } else {
                    try {
                        MessageResponse messageResponse = new Gson().fromJson(response.errorBody().string(), MessageResponse.class);
                        Toast.makeText(AuthenticationActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                hideProgressIndicator();
                Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
            }
        });
        Log.i("log", "handleLogin: 3");

    }

    private void handleRegistration() {
        showProgressIndicator();
        Hasura.auth.register(new AuthRequest(username.getText().toString(), password.getText().toString())).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                hideProgressIndicator();
                if (response.isSuccessful()) {
                    Hasura.setSession(response.body());
                    MainActivity.startActivity(AuthenticationActivity.this);
                } else {
                    try {
                        MessageResponse messageResponse = new Gson().fromJson(response.errorBody().string(), MessageResponse.class);
                        Toast.makeText(AuthenticationActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                hideProgressIndicator();
                Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
