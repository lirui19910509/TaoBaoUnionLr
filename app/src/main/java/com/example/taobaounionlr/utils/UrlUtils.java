package com.example.taobaounionlr.utils;

public class UrlUtils {
    public static String createHomePagerUrl(int materialId,int page){
        return "discovery/"+materialId+"/"+page;
    }
}
