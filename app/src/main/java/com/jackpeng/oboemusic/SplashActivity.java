package com.jackpeng.oboemusic;

import android.os.Bundle;

import com.jackpeng.lib_baselibrary.mvp.BaseActivity;
import com.jackpeng.oboemusic.utils.Proxy;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashPresenter.IView{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();



    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //1.5秒进入
        mvpPresenter.countDown();
    }


    @Override
    public void gotoMain() {
        Proxy.goNoteEarTraining(mActivity);
        //把自己干掉
        finish();
    }
}