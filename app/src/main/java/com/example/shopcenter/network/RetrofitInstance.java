package com.example.shopcenter.network;

import com.example.shopcenter.model.ProductItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final Type PRODUCT_TYPE = ProductItem.class;
    private static final Type PRODUCT_LIST_TYPE = new TypeToken<List<ProductItem>>() {
    }.getType();
    private static final Object PRODUCT_TYPE_ADAPTER = new GetProductItemDeserializer();
    private static final Object PRODUCT_LIST_TYPE_ADAPTER = new GetProductItemsDeserializer();

    private static Retrofit getInstance(Type type, Object typeAdapter) {

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

    public static final Retrofit
            RETROFIT_PRODUCT = getInstance(PRODUCT_TYPE, PRODUCT_TYPE_ADAPTER);
    public static final Retrofit
            RETROFIT_LIST_PRODUCT = getInstance(PRODUCT_LIST_TYPE, PRODUCT_LIST_TYPE_ADAPTER);
}
