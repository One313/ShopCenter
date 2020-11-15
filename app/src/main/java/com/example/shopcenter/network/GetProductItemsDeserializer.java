package com.example.shopcenter.network;

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

public class GetProductItemsDeserializer implements JsonDeserializer<List<ProductItem>> {

    @Override
    public List<ProductItem> deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context)
            throws JsonParseException {
        List<ProductItem> productItems = new ArrayList<>();
        List<ImageItem> imageItems = new ArrayList<>();

        JsonArray jsonArray = json.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj = jsonArray.get(i).getAsJsonObject();

            JsonArray images = jsonObj.getAsJsonArray("images");
            for (int j = 0; j < images.size(); j++) {
                JsonObject imageObj = images.get(j).getAsJsonObject();

                imageItems.add(new ImageItem(
                        imageObj.get("id").getAsInt(),
                        imageObj.get("name").getAsString(),
                        imageObj.get("src").getAsString()
                ));
            }

            productItems.add(new ProductItem(jsonObj.get("id").getAsInt(),
                    jsonObj.get("name").getAsString(),
                    jsonObj.get("price").getAsString(),
                    jsonObj.get("weight").getAsString(),
                    imageItems.toArray(new ImageItem[0])));

            imageItems.clear();
        }

        return productItems;
    }
}
