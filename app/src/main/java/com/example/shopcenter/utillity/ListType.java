package com.example.shopcenter.utillity;

import java.io.Serializable;

public enum ListType implements Serializable {
    RECENT_PRODUCT , POPULAR_PRODUCT , RATING_PRODUCT;

    public static String getRecent(){
        return "جدیدترین کالاها";
    }

    public static String getPopular(){
        return "پربازدیدترین کالاها";
    }
    public static String getRating(){
        return "بیشترین امتیاز";
    }

}
