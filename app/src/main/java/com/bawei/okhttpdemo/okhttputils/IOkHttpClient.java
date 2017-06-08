package com.bawei.okhttpdemo.okhttputils;

import com.bawei.okhttpdemo.CookiesManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Bonnenu1t丶 on 2017/5/26.
 */

public class IOkHttpClient {

    static OkHttpClient client;

    static ExecutorService executorService;

    static {
        executorService = Executors.newCachedThreadPool();

        client = new OkHttpClient.Builder()
                .cookieJar(new CookiesManager())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    //异步发送请求

    public static void getAsyn(final Request request, final DataHandler dataHandler){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                client.newCall(request).enqueue(new ICallBack(dataHandler, (String) request.tag()));
            }
        });

    }
}
