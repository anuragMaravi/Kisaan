package com.mearkiphi.kisaan.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.SelectItemDetailsQuery2;
import com.mearkiphi.kisaan.models.TodoRecord;
import com.mearkiphi.kisaan.others.GPSTracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BuyActivity extends AppCompatActivity {
    private ImageView imageViewItem;
    private TextView textViewRate, textViewSubType, textViewLocation;
    private FloatingActionButton fab, fab2;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(getIntent().getStringExtra("sub_category"));
        fetchTodosFromDB();
    }

    private void fetchTodosFromDB() {
        Hasura.db.getItemDetails2(new SelectItemDetailsQuery2(getIntent().getIntExtra("id", 0))).enqueue(new Callback<List<TodoRecord>>() {

            @Override
            public void onResponse(Call<List<TodoRecord>> call, final Response<List<TodoRecord>> response) {
                Log.i("ItemDetails2", "onResponse: " + response.body().get(0).getImage());
                if (response.isSuccessful()) {
                    imageViewItem = (ImageView) findViewById(R.id.imageViewItem);
                    textViewRate = (TextView) findViewById(R.id.textViewRate);
                    textViewSubType = (TextView) findViewById(R.id.textViewSubType);
                    textViewLocation = (TextView) findViewById(R.id.textViewLocation);
                    fab = (FloatingActionButton) findViewById(R.id.fab);
                    fab2 = (FloatingActionButton) findViewById(R.id.fab2);

                    Glide.with(getApplicationContext()).load(response.body().get(0).getImage()).into(imageViewItem);
                    textViewRate.setText("₹" + String.valueOf(response.body().get(0).getRate()));
                    textViewSubType.setText(response.body().get(0).getSubCategory());
                    textViewLocation.setText(response.body().get(0).getLocation());

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9876543210"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (ActivityCompat.checkSelfPermission(BuyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                Toast.makeText(BuyActivity.this, "Please give permission.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            startActivity(intent);
                        }
                    });


                    fab2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gps = new GPSTracker(BuyActivity.this);
                            double latitude = gps.getLatitude();
                            double longitude = gps.getLongitude();
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("http://maps.google.com/maps?saddr=" + latitude + ", " + longitude + "&daddr=" + response.body().get(0).getLocation()));
                            if (ActivityCompat.checkSelfPermission(BuyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                Toast.makeText(BuyActivity.this, "Please give permission.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            startActivity(intent);
                        }
                    });

//                    progressBar.setVisibility(View.GONE);

                } else {
//                    handleError(response.errorBody());
                    Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TodoRecord>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
