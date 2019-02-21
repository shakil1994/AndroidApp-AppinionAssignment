package com.example.shakil.assignment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakil.assignment.Model.Model;
import com.example.shakil.assignment.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

    AppCompatSpinner spinner_product_group, spinner_literature, spinner_physician_sample, spinner_gift;
    EditText edt_literature, edt_physician, edt_gift, edt_accompanied, edt_remarks;
    Button btn_submit;

    public static final String baseUrl = "https://raw.githubusercontent.com/";
    private RetrofitService retrofitService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        spinner_product_group = findViewById(R.id.spinner_product_group);
        spinner_literature = findViewById(R.id.spinner_literature);
        spinner_physician_sample = findViewById(R.id.spinner_physician_sample);
        spinner_gift = findViewById(R.id.spinner_gift);

        /*spinner_product_group.setOnItemSelectedListener(this);
        spinner_literature.setOnItemSelectedListener(this);
        spinner_physician_sample.setOnItemSelectedListener(this);
        spinner_gift.setOnItemSelectedListener(this);*/

        edt_literature = findViewById(R.id.edt_literature);
        edt_physician = findViewById(R.id.edt_physician);
        edt_gift = findViewById(R.id.edt_gift);
        edt_accompanied = findViewById(R.id.edt_accompanied);
        edt_remarks = findViewById(R.id.edt_remarks);

        getAllSpinnerData();

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spn = spinner_product_group.getSelectedItem().toString() + " " + spinner_literature.getSelectedItem().toString() + " " +
                        spinner_physician_sample.getSelectedItem().toString() + " " + spinner_gift.getSelectedItem().toString();

                if (edt_accompanied.length() > 0 && edt_gift.length() > 0 && edt_literature.length() > 0 && edt_physician.length() > 0 && edt_remarks.length() > 0) {
                    edt_gift.setText("");
                    edt_literature.setText("");
                    edt_physician.setText("");
                    edt_accompanied.setText("");
                    edt_remarks.setText("");

                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill up all information.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }*/

    public void getAllSpinnerData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory
                        .create()).build();
        retrofitService = retrofit.create(RetrofitService.class);
        String endURL;
        endURL = String.format("appinion-dev/intern-dcr-data/master/data.json");
        Call<Model> call = retrofitService.getAllItems(endURL);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.code() == 200) {

                    Model model = response.body();

                    if (model.getGiftList().isEmpty()) {
                        Toast.makeText(MainActivity.this, "No data found.", Toast.LENGTH_SHORT).show();
                    } else {
                        int i;
                        List<String> categoriesProductGroupList = new ArrayList<>();
                        List<String> categoriesLiteratureList = new ArrayList<>();
                        List<String> categoriesPhysicianSampleList = new ArrayList<>();
                        List<String> categoriesProductGiftList = new ArrayList<>();

                        categoriesProductGroupList.add(0, "Choose");
                        for (i = 0; i < model.getProductGroupList().size(); i++) {
                            categoriesProductGroupList.add(model.getProductGroupList().get(i).getProductGroup());
                        }

                        categoriesLiteratureList.add(0, "Choose");
                        for (i = 0; i < model.getLiteratureList().size(); i++) {
                            categoriesLiteratureList.add(model.getLiteratureList().get(i).getLiterature());
                        }

                        categoriesPhysicianSampleList.add(0, "Choose");
                        for (i = 0; i < model.getPhysicianSampleList().size(); i++) {
                            categoriesPhysicianSampleList.add(model.getPhysicianSampleList().get(i).getSample());
                        }

                        categoriesProductGiftList.add(0, "Choose");
                        for (i = 0; i < model.getGiftList().size(); i++) {
                            categoriesProductGiftList.add(model.getGiftList().get(i).getGift());
                        }

                        //Style and populate spinner
                        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, categoriesProductGroupList);
                        //Dropdown layout style
                        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Attaching data adapter to spinner
                        spinner_product_group.setAdapter(productAdapter);

                        //Style and populate spinner
                        ArrayAdapter<String> literatureAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, categoriesLiteratureList);
                        //Dropdown layout style
                        literatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Attaching data adapter to spinner
                        spinner_literature.setAdapter(literatureAdapter);

                        //Style and populate spinner
                        ArrayAdapter<String> physicianAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, categoriesPhysicianSampleList);
                        //Dropdown layout style
                        physicianAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Attaching data adapter to spinner
                        spinner_physician_sample.setAdapter(physicianAdapter);

                        //Style and populate spinner
                        ArrayAdapter<String> giftAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, categoriesProductGiftList);
                        //Dropdown layout style
                        giftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Attaching data adapter to spinner
                        spinner_gift.setAdapter(giftAdapter);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                try {
                    Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
