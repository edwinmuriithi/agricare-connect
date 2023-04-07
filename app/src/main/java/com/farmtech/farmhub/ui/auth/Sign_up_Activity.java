package com.farmtech.farmhub.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.farmtech.farmhub.R;
import com.farmtech.farmhub.databinding.ActivitySignUp2Binding;
import com.farmtech.farmhub.api.ApiClient;
import com.farmtech.farmhub.model.signup.RegisterRequest;
import com.farmtech.farmhub.model.signup.RegisterResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up_Activity extends AppCompatActivity {

    private String userName;
    SharedPreferences sharedPreferences;
    public static final String myPreferences = "myPref";
    private ActivitySignUp2Binding binding;
    public String countySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final Spinner spinner = (Spinner) findViewById(R.id.county_spinner);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select your county");
        categories.add("Mombasa");
        categories.add("Kwale");
        categories.add("Kilifi");
        categories.add("TanaRiver");
        categories.add("Lamu");
        categories.add("Taita/Taveta");
        categories.add("Garissa");
        categories.add("Wajir");
        categories.add("Mandera");
        categories.add("Marsabit");
        categories.add("Isiolo");
        categories.add("Meru");
        categories.add("Tharaka-Nithi");
        categories.add("Embu");
        categories.add("Kitui");
        categories.add("Machakos");
        categories.add("Makueni");
        categories.add("Nyandarua");
        categories.add("Nyeri");
        categories.add("Kirinyaga");
        categories.add("Murang'a");
        categories.add("Kiambu");
        categories.add("Turkana");
        categories.add("WestPokot");
        categories.add("Samburu");
        categories.add("TransNzoia");
        categories.add("UasinGishu");
        categories.add("Elgeyo/Marakwet");
        categories.add("Nandi");
        categories.add("Baringo");
        categories.add("Laikipia");
        categories.add("Nakuru");
        categories.add("Narok");
        categories.add("Kajiado");
        categories.add("Kericho");
        categories.add("Bomet");
        categories.add("Kakamega");
        categories.add("Vihiga");
        categories.add("Bungoma");
        categories.add("Busia");
        categories.add("Siaya");
        categories.add("Kisumu");
        categories.add("HomaBay");
        categories.add("Migori");
        categories.add("Kisii");
        categories.add("Nyamira");
        categories.add("Nairobi");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories){

        @Override
        public boolean isEnabled(int position){
            // Disable the first item from Spinner
            // First item will be use for hint
            return position != 0;
        }
        @Override
        public View getDropDownView(
        int position, View convertView,
                @NonNull ViewGroup parent) {

            // Get the item view
            View view = super.getDropDownView(position, convertView, parent);
            TextView textView = (TextView) view;
            if(position == 0){
                // Set the hint text color gray
                textView.setTextColor(Color.GRAY);
            }
            else { textView.setTextColor(Color.BLACK); }
            return view;
        }
    };

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        binding.countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                    countySelected = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        String UserName = binding.fname.getText().toString();
        String phone = binding.signUpPhone.getText().toString();
        if (sharedPreferences.contains(UserName)){
            binding.fname.setText(sharedPreferences.getString(UserName,""));
        }
        if (sharedPreferences.contains(phone)){
            binding.signUpPhone.setText(sharedPreferences.getString(phone,""));
        }

        binding.loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.fname.getText().toString().isEmpty() || binding.signUpPhone.getText().toString().isEmpty() ||
                   binding.confirmPhone.getText().toString().isEmpty() || binding.signupPassword.getText().toString().isEmpty() ||
                   binding.confirmPassword.getText().toString().isEmpty() || countySelected == "Select your county"){
                    Toast.makeText(Sign_up_Activity.this, "Fill in all fields correctly",Toast.LENGTH_LONG).show();

                }else{
                    if(binding.signUpPhone.getText().toString().trim().equals(binding.confirmPhone.getText().toString().trim())) {
                        if (binding.signupPassword.getText().toString().trim().equals(binding.confirmPassword.getText().toString().trim())) {
                            RegisterRequest registerRequest = new RegisterRequest();
                            registerRequest.setNames(binding.fname.getText().toString().trim());
                            registerRequest.setPhone(binding.signUpPhone.getText().toString().trim());
                            registerRequest.setCounty(countySelected);
                            registerRequest.setPassword(binding.signupPassword.getText().toString().trim());
                            createNewUser(registerRequest);
                        } else {
                            Toast.makeText(Sign_up_Activity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Sign_up_Activity.this, "Phone Numbers do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

        private void createNewUser(RegisterRequest registerRequest) {
        userName = binding.fname.getText().toString().trim();

        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService(this).registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "Registration Successful";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Sign_up_Activity.this,LoginActivity.class));
                    finish();

                }else{
                     String message = "An error occurred please try again";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Sign_up_Activity.this, "Throwable "+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}









