package com.bawei.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Bonnenu1t丶 on 2017/5/27.
 */

public class CacheInterecepter implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        return response.newBuilder()
                .header("Cache-Control","max-age=60")
                .build();
    }
}
