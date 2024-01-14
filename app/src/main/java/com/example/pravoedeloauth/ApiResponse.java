package com.example.pravoedeloauth;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;


    public ApiResponse(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return (code);
    }

    public String getStatus() {
        return (status);
    }
}