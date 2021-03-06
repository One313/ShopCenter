package com.example.shopcenter.data.network.retrofit;

import com.example.shopcenter.data.model.customer.Customer;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.data.model.category.Category;
import com.example.shopcenter.data.network.parameter.RequestParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WooCommerceService {
    @GET(".")
    Call<List<Product>> listAllProduct(@QueryMap Map<String, String> products ,
                                       @Query("page") int page );

    @GET("{id}/")
    Call<Product> product( @Path("id") String id , @QueryMap Map<String, String> products );

    @GET(".")
    Call<List<Product>> searchProducts(@QueryMap Map<String, String> products ,
                                       @Query("search") String search ,
                                       @Query("page") int page);

    @GET(".")
    Call<List<Category>> SubCategories( @QueryMap Map<String, String> products ,
                                        @Query("parent") int parent);

    @GET(".")
    Call<List<Product>> productListOfCategory(
            @QueryMap Map<String, String> products ,
            @Query("category") int category,
            @Query("page") int page);

    @POST(RequestParams.CUSTOMER_CREATE_PATH)
    Call<Customer> registerCustomer(@Body Customer customer);

    @GET(RequestParams.CUSTOMER_CREATE_PATH)
    Call<List<Customer>> getCustomerByEmail(@Query("email") String email);
}
