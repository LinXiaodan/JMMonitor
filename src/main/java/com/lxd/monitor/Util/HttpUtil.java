package com.lxd.monitor.Util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {
    private static HttpClient httpClientInstance;

    static{
        init();
    }

    public static void init(){
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager());
        SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
        socketConfigBuilder.setSoKeepAlive(true).setTcpNoDelay(true);
        socketConfigBuilder.setSoTimeout(10000);
        SocketConfig socketConfig = socketConfigBuilder.build();
        httpClientBuilder.setDefaultSocketConfig(socketConfig);
        httpClientInstance = httpClientBuilder.build();
    }

    public static String get(String url,String queryStrings){
        String uri = url;
        if(StringUtils.isNotEmpty(queryStrings)){
            uri += "?"+queryStrings;
        }
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = null;
        String srtResult = null;
        try {
            response = httpClientInstance.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(response.getEntity());//获得返回的结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return srtResult;
    }

    public static String get(String url){
        String uri = url;
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = null;
        String srtResult = null;
        try {
            response = httpClientInstance.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(response.getEntity());//获得返回的结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return srtResult;
    }

    public static String postFormData(String url, Map<String,String> params){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Set<String> paramKeys = params.keySet();
        List<NameValuePair> paramList = new ArrayList<>();
        for(String paramKey : paramKeys){
            paramList.add(new BasicNameValuePair(paramKey,params.get(paramKey)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            HttpResponse response = httpClientInstance.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String postJson(String url, JSONObject jsonObject){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        httpPost.setEntity(entity);
        String result = null;
        try {
            HttpResponse response = httpClientInstance.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
