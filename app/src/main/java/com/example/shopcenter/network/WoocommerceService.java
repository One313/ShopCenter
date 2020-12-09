package com.example.shopcenter.network;

import com.example.shopcenter.model.Category;
import com.example.shopcenter.model.ProductItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WoocommerceService {

    @GET("products")
    Call<List<ProductItem>> listItems(
            @Query("page") int page,
            @QueryMap Map<String, String> options
    );

    @GET("products/{id}")
    Call<ProductItem> item(
            @Path("id") int id,
            @QueryMap Map<String, String> options
    );

    @GET("products/categories")
    Call<List<Category>> listCategories(
            @Query("page") int page,
            @Query("parent") int parent,
            @QueryMap Map<String, String> options
    );

    @GET("products")
    Call<List<ProductItem>> listItemsInCategories(
            @Query("page") int page,
            @Query("category") int categoryId,
            @QueryMap Map<String, String> options
    );
}
