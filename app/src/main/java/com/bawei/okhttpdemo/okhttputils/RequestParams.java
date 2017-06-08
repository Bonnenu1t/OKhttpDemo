package com.bawei.okhttpdemo.okhttputils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Bonnenu1t丶 on 2017/5/26.
 */

public class RequestParams {

    Map<String,Object> params=new HashMap<String,Object>();

    String filekey;
    /**
     * map 封装成 RequestParams 对象
     * @param sources
     */
    public RequestParams(Map<String,Object> sources){
        for (Map.Entry<String, Object> entry : sources.entrySet()) {
            params.put(entry.getKey(),entry.getValue());
        }
    }

    //上传文件
    public RequestParams(Map<String,Object> sources,String filekey){
        for (Map.Entry<String, Object> entry : sources.entrySet()) {
            params.put(entry.getKey(),entry.getValue());
        }
        this.filekey=filekey;
    }
}
