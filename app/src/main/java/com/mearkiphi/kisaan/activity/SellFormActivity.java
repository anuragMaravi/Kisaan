package com.mearkiphi.kisaan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.InsertTodoQuery;
import com.mearkiphi.kisaan.models.TodoReturningResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SellFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView imageViewItem;
    private EditText editTextLocation, editTextRate;
    private TextView customTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_form);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Sell " + getIntent().getStringExtra("item_name"));
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
//        List<String> categories = new ArrayList<String>();
//        categories.add("Langra");
//        categories.add("Dashehari");
//        categories.add("Alphonso");
//        categories.add("Totapuri");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SellFormActivity.this, android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextRate = (EditText) findViewById(R.id.editTextRate);
        imageViewItem = (ImageView) findViewById(R.id.imageViewItem);
        customTextView = (TextView) findViewById(R.id.customTextView);
        customTextView.setText(getIntent().getStringExtra("item_name"));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(imageViewItem);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void Submit(View view) {

        addATodo();
    }

//    private void addATodo() {
//        Hasura.db.addATodo(new InsertTodoQuery(Hasura.getUserId(), editTextLocation.getText().toString().trim(), "Vegetables", Integer.parseInt(editTextRate.getText().toString()), "Apples")).enqueue(new Callback<TodoReturningResponse>() {
//            @Override
//            public void onResponse(Call<TodoReturningResponse> call, Response<TodoReturningResponse> response) {
//                if (response.isSuccessful()) {
//
//                    TodoRecord record = new TodoRecord(Hasura.getUserId(), editTextLocation.getText().toString().trim(), "Vegetables", Integer.parseInt(editTextRate.getText().toString()), "Apples");
//                    record.setId(response.body().getTodoRecords().get(0).getId());
////                    adapter.addData(record);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TodoReturningResponse> call, Throwable t) {
////                showErrorAlert("PLease ensure that you have a working internet connection",null);
//            }
//        });
//    }

    private void addATodo() {
        Hasura.db.addATodo(new InsertTodoQuery(editTextLocation.getText().toString().trim(),Hasura.getUserId(),Integer.parseInt(editTextRate.getText().toString()), getIntent().getStringExtra("type"), getIntent().getStringExtra("item_name"), getIntent().getStringExtra("image"))).enqueue(new Callback<TodoReturningResponse>() {
            @Override
            public void onResponse(Call<TodoReturningResponse> call, Response<TodoReturningResponse> response) {
                if (response.isSuccessful()) {
//                    TodoRecord record = new TodoRecord("Location",Hasura.getUserId(),"Hello");
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TodoReturningResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
            }
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
