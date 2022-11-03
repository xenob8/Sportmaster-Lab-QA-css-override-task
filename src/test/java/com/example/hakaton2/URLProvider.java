package com.example.hakaton2;

import java.io.File;
import java.net.URL;

public class URLProvider {
    public static String getUrl(String url){
        if (!isURL(url)) {
            url = new File(url).getAbsolutePath();
        }
        return url;
    }

    private static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
