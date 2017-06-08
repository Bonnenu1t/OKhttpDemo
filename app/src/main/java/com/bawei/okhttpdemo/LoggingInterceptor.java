package com.bawei.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Bonnenu1t丶 on 2017/5/27.
 */

public class LoggingInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1=System.nanoTime();
        System.out.println(" request  = " + String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        long t2=System.nanoTime();
        Response response = chain.proceed(request);
        System.out.println("response  " + String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
