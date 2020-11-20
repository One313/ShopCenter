package com.example.shopcenter.network;

import com.example.shopcenter.model.ProductItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface WoocommerceService {

    @GET("products")
    Call<List<ProductItem>> listItems(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<ProductItem> item(@Path("id") String id, @QueryMap Map<String, String> options);
}
