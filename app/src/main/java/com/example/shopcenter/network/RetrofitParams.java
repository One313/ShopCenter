package com.example.shopcenter.network;

import java.util.HashMap;
import java.util.Map;

public class RetrofitParams {

    public static final String BASE_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_b5f203e4089387857d28708a7eb9cdaf090f4237";
    public static final String CONSUMER_SECRET = "cs_ea4c141782826fdeaac413d0b0c9adbdc2f916f2";
    public static final String RATING = "rating";
    public static final String POPULARITY = "popularity";

    public static Map<String, String> QUERY_OPTIONS = new HashMap<String, String>() {
        {
            put("consumer_key", CONSUMER_KEY);
            put("consumer_secret", CONSUMER_SECRET);
        }
    };

    public static Map<String, String> QUERY_RATING = new HashMap<String, String>() {
        {
            putAll(QUERY_OPTIONS);
            put("orderby", RATING);
        }
    };

    public static Map<String, String> QUERY_POPULARITY = new HashMap<String, String>() {
        {
            putAll(QUERY_OPTIONS);
            put("orderby", POPULARITY);
        }
    };
}
