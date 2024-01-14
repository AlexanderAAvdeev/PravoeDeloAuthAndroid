package com.example.pravoedeloauth;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText phoneNumberInput;
    private EditText passwordInput;
    private Button sendCodeButton;
    private ApiService apiService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        passwordInput = findViewById(R.id.passwordInput);
        sendCodeButton = findViewById(R.id.sendCodeButton);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY); // Установите нужный уровень логирования
        httpClient.addInterceptor(logging);

        OkHttpClient client = httpClient.build();
        // Use the custom OkHttpClient with Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lk.pravoe-delo.su").client(client) //OkHttpClient с интерцептором
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim(); // Get password from EditTex
                if (!phoneNumber.isEmpty()) {
                    // Use the correct API method that expects a String parameter
                    Call<ApiResponse> call = apiService.getCode(phoneNumber);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                                ApiResponse apiResponse = response.body();
                                if (apiResponse != null) {
                                    int code = apiResponse.getCode();
                                    String status = apiResponse.getStatus();
                                    // Handle the received code and status
                                    Toast.makeText(MainActivity.this, "Code: " + code + ", Status: " + status, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle unsuccessful response
                                Toast.makeText(MainActivity.this, "Unsuccessful response", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            // Handle errors, such as network errors or exceptions
                            Toast.makeText(MainActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}