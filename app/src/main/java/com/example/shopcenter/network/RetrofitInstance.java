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

    public static final String BASE_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    public static final String CONSUMER_KEY = "ck_b5f203e4089387857d28708a7eb9cdaf090f4237";
    public static final String CONSUMER_SECRET = "cs_ea4c141782826fdeaac413d0b0c9adbdc2f916f2";

    public static Map<String, String> QUERY_OPTIONS = new HashMap<String, String>() {
        {
            put("consumer_key", CONSUMER_KEY);
            put("consumer_secret", CONSUMER_SECRET);
        }
    };


    public static Retrofit getInstance(Type type, Object typeAdapter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_PATH)
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
