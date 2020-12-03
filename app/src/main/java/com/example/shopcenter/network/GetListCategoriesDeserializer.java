package com.example.shopcenter.network;

import com.example.shopcenter.model.Category;
import com.example.shopcenter.model.ImageCategory;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetListCategoriesDeserializer implements JsonDeserializer<List<Category>> {

    @Override
    public List<Category> deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context)
            throws JsonParseException {
        List<Category> categories = new ArrayList<>();

        JsonArray jsonCategories = json.getAsJsonArray();
        for (int i = 0; i < jsonCategories.size(); i++) {
            JsonObject jsonCategory = jsonCategories.get(i).getAsJsonObject();

            categories.add(
                    new Category(
                            jsonCategory.get("id").getAsInt(),
                            jsonCategory.get("name").getAsString(),
                            jsonCategory.get("parent").getAsInt(),
                            new ImageCategory(
                                    jsonCategory.get("image").getAsJsonObject().get("id").getAsInt(),
                                    jsonCategory.get("image").getAsJsonObject().get("name").getAsString(),
                                    jsonCategory.get("image").getAsJsonObject().get("src").getAsString()
                            )
                    ));
        }
        return categories;
    }
}
