package com.bawei.okhttpdemo.okhttputils;

/**
 * Created by Bonnenu1t丶 on 2017/5/26.
 */

public class DataHandler {

    public IResponseListener listener;

    //需要转化的实体类
    public Class clazz;

    public DataHandler(IResponseListener listener){
        this.listener=listener;
    }

    public DataHandler(IResponseListener listener,Class clazz){
        this.listener=listener;
        this.clazz=clazz;
    }
}
