package com.jackpeng.lib_baselibrary.mvp;

import android.app.Activity;
import android.content.Context;

public interface IBaseView {
    //拿到activity实例
    Activity getThisActivity();
    //拿到context
    Context getThisContext();
    //弹Toast
    void T(String msg);
    //打Log
    void D(String msg);
    void E(String msg);
    //显示进度条
    void showProgressBar(String title, String message);
    //返回码？
    void resultOK();
    //返回码？
    void resultCancel();
}
