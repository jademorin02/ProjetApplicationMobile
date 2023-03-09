package com.example.projetapplicationmobilemarkus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String BASE_URL = "https://cours.cegep3r.info/H2023/420606RI/GR04/";

    private static Retrofit retrofit;

    public static Retrofit getInstance(){

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

