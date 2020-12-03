package com.example.shopcenter.network;

import com.example.shopcenter.model.Category;
import com.example.shopcenter.model.ImageItem;
import com.example.shopcenter.model.ProductItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetProductItemDeserializer implements JsonDeserializer<ProductItem> {
    @Override
    public ProductItem deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context)
            throws JsonParseException {
        List<ImageItem> imageItems = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        JsonObject product = json.getAsJsonObject();

        JsonArray images = product.getAsJsonArray("images");
        for (int i = 0; i < images.size(); i++) {
            JsonObject image = images.get(i).getAsJsonObject();
            imageItems.add(
                    new ImageItem(
                            image.get("id").getAsInt(),
                            image.get("name").getAsString(),
                            image.get("src").getAsString())
            );
        }

        JsonArray jsonCategories = product.getAsJsonArray("categories");
        for (int i = 0; i < jsonCategories.size(); i++) {
            JsonObject category = jsonCategories.get(i).getAsJsonObject();
            categories.add(
                    new Category(
                            category.get("id").getAsInt(),
                            category.get("name").getAsString())
            );
        }

        return new ProductItem(
                product.get("id").getAsInt(),
                product.get("name").getAsString(),
                product.get("description").getAsString(),
                product.get("price").getAsString(),
                imageItems.toArray(new ImageItem[]{}),
                categories.toArray(new Category[]{}));
    }
}
