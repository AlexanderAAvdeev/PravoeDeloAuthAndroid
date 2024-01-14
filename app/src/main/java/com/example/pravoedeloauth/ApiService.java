package com.example.pravoedeloauth;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/api/v1/getCode")
    Call<ApiResponse> getCode(@Query("login") String phoneNumber);

    @GET("/api/v1/getToken")
    Call<ApiResponse> getToken(@Query("login") String phoneNumber, @Query("password") String password); // Assuming token is a string, modify the response type accordingly

//    @GET("/api/v1/regenerateCode")
//    Call<String> regenerateCode(@Query("code") String code); // Assuming code is a string
//
//    @POST("/api/v1/addFurtherPhone")
//    Call<ApiResponse> addFurtherPhone(@Query("phone") String phone); // Add additional phone
//
//    @POST("/api/v1/confirmFurtherPhone")
//    Call<String> confirmFurtherPhone(
//            @Query("phone") String phone,
//            @Query("code") String code); // Confirm additional phone with code
//
//    @POST("/api/v1/deleteFurtherPhone")
//    Call<String> deleteFurtherPhone(@Query("phone") String phone); // Delete additional phone
//
//    @POST("/api/v1/changeCode")
//    Call<String> changeCode(@Query("code") String code); // Change authorization code
//
}

