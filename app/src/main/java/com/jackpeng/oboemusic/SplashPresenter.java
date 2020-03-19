package com.jackpeng.oboemusic;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jackpeng.lib_baselibrary.mvp.BasePresenter;
import com.jackpeng.lib_baselibrary.mvp.IBaseView;

public class SplashPresenter extends BasePresenter<SplashPresenter.IView> {

    private static final long SPLASH_SECONDS = 1500;

    public void countDown(){
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mvpView.gotoMain();
            }
        };
        handler.sendEmptyMessageDelayed(0, SPLASH_SECONDS);
    }

    public interface IView extends IBaseView {
        //跳转到主界面
        void gotoMain();
    }
}
