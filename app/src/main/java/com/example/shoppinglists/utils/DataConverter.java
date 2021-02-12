package com.example.shoppinglists.utils;

import androidx.room.TypeConverter;
import com.example.shoppinglists.data.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String convertProductsListToJSON(List<Product> productsList) {
        if (productsList == null || productsList.isEmpty()) {
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        String json = gson.toJson(productsList, type);

        return json;
    }

    @TypeConverter
    public List<Product> convertJSONToProductsList(String productsJSON) {
        if (productsJSON == null || productsJSON.isEmpty()) {
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        List<Product> productsList = gson.fromJson(productsJSON, type);

        return productsList;
    }

}
