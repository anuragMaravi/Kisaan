package com.mearkiphi.kisaan.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(getIntent().getStringExtra("sub_category"));
        checkPermissions();
        fetchTodosFromDB();
    }

    private void checkPermissions() {
        {
            if(ActivityCompat.checkSelfPermission(BuyActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(BuyActivity.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(BuyActivity.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[2])){
                    //Show Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuyActivity.this);
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Location permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(BuyActivity.this,permissionsRequired,PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (false) {
                    //Previously Permission Request was cancelled with 'Dont Ask Again',
                    // Redirect to Settings after showing Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuyActivity.this);
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Location permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            //sentToSettings = true;
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                            Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Location", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }  else {
                    //just request the permission
                    ActivityCompat.requestPermissions(BuyActivity.this,permissionsRequired,PERMISSION_CALLBACK_CONSTANT);
                }

                //txtPermissions.setText("Permissions Required");

//                SharedPreferences.Editor editor = permissionStatus.edit();
//                editor.putBoolean(permissionsRequired[0],true);
//                editor.commit();
            }
        }
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CALLBACK_CONSTANT){
            //check if all permissions are granted
            boolean allgranted = false;
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if(allgranted){
                proceedAfterPermission();
            } else if(ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(BuyActivity.this,permissionsRequired[2])){
             //   txtPermissions.setText("Permissions Required");
                AlertDialog.Builder builder = new AlertDialog.Builder(BuyActivity.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(BuyActivity.this,permissionsRequired,PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(BuyActivity.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }
    private void proceedAfterPermission() {
        //txtPermissions.setText("We've got all permissions");
        Toast.makeText(getBaseContext(), "We got All Permissions", Toast.LENGTH_LONG).show();
    }

}
