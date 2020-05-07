package com.jackpeng.oboemusic;

import android.app.Application;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Beta.autoInit = true;
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Beta.installTinker();
        Bugly.init(this, "2feefa9c4a", true);
    }
}
