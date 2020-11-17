package com.example.shopcenter.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getInstance(Type type, Object typeAdapter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitParams.BASE_PATH)
                .addConverterFactory(createGSONConverter(type, typeAdapter))
                .build();

        return retrofit;
    }

    private static Converter.Factory createGSONConverter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
