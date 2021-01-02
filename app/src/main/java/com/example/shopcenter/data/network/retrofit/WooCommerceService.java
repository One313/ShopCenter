package com.example.shopcenter.data.network.retrofit;

import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.data.model.category.Category;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WooCommerceService {
    @GET(".")
    Call<List<Product>> listAllProduct(@QueryMap Map<String, String> products );

    @GET("{id}/")
    Call<Product> product( @Path("id") String id , @QueryMap Map<String, String> products );

    @GET(".")
    Call<List<Category>> SubCategories( @QueryMap Map<String, String> products , @Query("parent") int parent);
}