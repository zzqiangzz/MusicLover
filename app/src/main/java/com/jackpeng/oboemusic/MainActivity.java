package com.jackpeng.oboemusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jackpeng.lib_baselibrary.mvp.BaseActivity;
import com.jackpeng.oboemusic.utils.Proxy;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static Intent getThisIntent(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, MainActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_ear_note,R.id.btn_ear_interval
    ,R.id.btn_identify_note,R.id.btn_identify_interval})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_ear_note:
                Proxy.goNoteEarTraining(mActivity);
                break;
            case R.id.btn_ear_interval:
            case R.id.btn_identify_note:
            case R.id.btn_identify_interval:
                T("时间有限，正在使劲开发中！！！");
                break;
        }
    }
}
