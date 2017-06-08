package com.bawei.okhttpdemo.okhttputils;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Bonnenu1t丶 on 2017/5/26.
 */

public class IRequest {

    //创建一个get request

    /**
     *
     * @param requestParams  封装的参数
     * @param url  请求的接口地址
     * @return Request ==  封装好了 请求的参数和url 的 request
     */
    public static Request createGetRequest(RequestParams requestParams, String url){

        String resulturl=url;

        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if (requestParams.params.size()>0){
            builder.append("?");
            // www.baidu.com/login?username=111&password=123
            for (Map.Entry<String, Object> entry : requestParams.params.entrySet()) {
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
                builder.append("&");
            }
            resulturl=builder.substring(0,builder.length()-1);

        }

        return new Request.Builder().url(resulturl).tag(url).build();
    }


    //创建一个 post request

    /**
     *post
     * @param requestParams 封装的参数
     * @param url   请求的接口地址
     * @return Request ＝＝  封装好了 请求参数 和url 的request
     */
    public static Request createPostRequest(RequestParams requestParams,String url){
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String, Object> entry : requestParams.params.entrySet()) {
            builder.add(entry.getKey(), (String)  entry.getValue());

        }

        FormBody formBody = builder.build();

        return new Request.Builder().url(url).post(formBody).tag(url).build();
    }

    //创建一个 file request
    /**
     *  post
     * @param requestParams 封装的参数68
     * @param url   请求的接口地址
     * @return Request ＝＝  封装好了 请求参数（包含了file） 和url 的request
     */
    public static Request createFileRequest(RequestParams requestParams,String url){

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : requestParams.params.entrySet()) {
            if (entry.getValue() instanceof File) {
                String[] split = ((File) entry.getValue()).getPath().split("/");
                builder.addFormDataPart(entry.getKey(), split[split.length - 1], RequestBody.create(MediaType.parse("image/png"), (File) entry.getValue()));
            } else {
                builder.addFormDataPart(entry.getKey(), (String) entry.getValue());
            }
        }
        MultipartBody multipartBody = builder.build();
        return new Request.Builder().url(url).post(multipartBody).tag(url).build();
    }
}
