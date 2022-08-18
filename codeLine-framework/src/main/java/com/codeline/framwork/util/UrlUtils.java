package com.codeline.framwork.util;

import cn.hutool.core.util.URLUtil;

import java.net.URL;

/**
 * @author: syl
 * @Date: 2022/8/19 00:00
 * @Description:
 */
public class UrlUtils {

    public static String getDomain(String httpUrl){
        URL url = URLUtil.url(httpUrl);
        String host = url.getHost();
        String[] split = host.split(".com");
        if (split.length > 0){
            return split[0];
        }
        return null;
    }
}
