package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.data.database.entity.CartProduct;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.data.repository.CartProductDBRepository;
import com.example.shopcenter.data.repository.ProductRepository;

public class DetailFragmentViewModel extends AndroidViewModel {

    private ProductRepository mRepository = ProductRepository.getInstance();
    private CartProductDBRepository mCartProductDBRepository;
    private MutableLiveData<Product> mProductSubjectLiveData= new MutableLiveData<>();
    private LiveData<Product> mProductLiveData ;

    public DetailFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductLiveData = mRepository.getSingleProductLiveData();
        mCartProductDBRepository = CartProductDBRepository.getInstance(getApplication());
    }

    public LiveData<Product> getProductLiveData() {
        return mProductLiveData;
    }

    public MutableLiveData<Product> getProductSubjectLiveData() {
        return mProductSubjectLiveData;
    }

    public void setProductSubjectLiveData(Product product) {
        mProductSubjectLiveData.setValue(product);

    }

    public void fetchProduct(String id){
        mRepository.fetchProduct(id);
    }


    public void addProductToCart(int id){
        CartProduct cartProduct =
                new CartProduct(id,
                        "",
                        1);
        mCartProductDBRepository.insert(cartProduct);
    }
}
