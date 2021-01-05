package com.example.shopcenter.data.database.carttabledatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shopcenter.data.database.dao.CartProductDBDao;
import com.example.shopcenter.data.database.entity.CartProduct;

@Database(entities = {CartProduct.class}, version = 1 , exportSchema = false)
public abstract class CartProductDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "cart_product.db";

    public abstract CartProductDBDao getDao();

}
