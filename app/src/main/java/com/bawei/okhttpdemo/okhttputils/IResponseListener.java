package com.bawei.okhttpdemo.okhttputils;

/**
 * Created by Bonnenu1t丶 on 2017/5/26.
 */

public interface IResponseListener {

    //成功回调
    //tag 是网络回调的标识
    public void onSuccess(Object response,String tag);

    //失败回调
    public void onFailed(Object failed,String tag);

}
