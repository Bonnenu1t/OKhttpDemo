package com.bawei.okhttpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity implements View.OnClickListener {

    ExecutorService executorService= Executors.newFixedThreadPool(3);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.get_id).setOnClickListener(this);
        findViewById(R.id.get_syn).setOnClickListener(this);
        findViewById(R.id.post_id).setOnClickListener(this);
        findViewById(R.id.post_syn).setOnClickListener(this);
        findViewById(R.id.post_string).setOnClickListener(this);
        findViewById(R.id.post_file).setOnClickListener(this);
        findViewById(R.id.interceptor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.get_id :

                break;
            case R.id.get_syn :

                break;
            case R.id.post_id :

                break;
            case R.id.post_syn :
                postAsychronous();
                break;
            case R.id.post_string :
                    poststring();
                break;
            case R.id.post_file :

                break;
            case R.id.interceptor :

                break;
        }
    }

    //public static String photoCacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Bwei";
//Environment.getExternalStorageDirectory().getAbsolutePath() 获得外部存储目录的绝对路径
    public static String photoCacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Bwei";
    public void toPic(){
        //判断该文件是否存在
        if (!new File(photoCacheDir).exists()){
            //建立一个新的子目录
            new File(photoCacheDir).mkdirs();
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }


    private void poststring(){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String postBody = ""
                            + "Releases\n"
                            + "--------\n"
                            + "\n"
                            + " * _1.0_ May 6, 2013\n"
                            + " * _1.1_ June 15, 2013\n"
                            + " * _1.2_ August 11, 2013\n";

                    MediaType MEDIA_TYPE_MARKDOWN
                            = MediaType.parse("text/x-markdown; charset=utf-8");

                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
                            .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody)).build();

                    Response response =  client.newCall(request).execute() ;

                    System.out.println("response = " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String url = "http://qhb.2dyt.com/Bwei/login" ;
    private void postSynchronous(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder().add("username", "18701317750").add("password", "123456").add("postkey", "1503d").build();

                //addHeader 添加头
                Request request = new Request.Builder().url(url).addHeader("key", "value").build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    System.out.println("response = " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //异步 post
    private void postAsychronous(){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int cacheSize = 10 * 1024 * 1024; // 10 MiB


                    Cache cache = new Cache(getCacheDir(),cacheSize);


                    OkHttpClient client = new OkHttpClient.Builder().cache(cache).build() ;


                    CacheControl cacheControl = new CacheControl.Builder().maxAge(1, TimeUnit.MINUTES).build();

                    Request request = new Request.Builder()
                            .url("http://publicobject.com/helloworld.txt")
                            .cacheControl(cacheControl)
                            .build();

                    Response response1 = client.newCall(request).execute();
                    if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

                    String response1Body = response1.body().string();
                    System.out.println("Response 1 response:          " + response1);
                    System.out.println("Response 1 cache response:    " + response1.cacheResponse());
                    System.out.println("Response 1 network response:  " + response1.networkResponse());

                    Response response2 = client.newCall(request).execute();
                    if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);

                    String response2Body = response2.body().string();
                    System.out.println("Response 2 response:          " + response2);
                    System.out.println("Response 2 cache response:    " + response2.cacheResponse());
                    System.out.println("Response 2 network response:  " + response2.networkResponse());

                    System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
