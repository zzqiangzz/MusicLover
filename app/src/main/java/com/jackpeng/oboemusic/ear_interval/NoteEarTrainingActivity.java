package com.jackpeng.oboemusic.ear_interval;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jackpeng.lib_baselibrary.mvp.BaseActivity;
import com.jackpeng.lib_baselibrary.mvp.BaseEarActivity;
import com.jackpeng.oboemusic.R;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteEarTrainingActivity extends BaseEarActivity {
    @BindView(R.id.btn_c_note)
    Button mCNote;

    public static Intent getThisIntent(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, NoteEarTrainingActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ear_note;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_c_note, R.id.btn_cd_note, R.id.btn_d_note, R.id.btn_de_note, R.id.btn_e_note,
            R.id.btn_f_note, R.id.btn_fg_note, R.id.btn_g_note, R.id.btn_ga_note, R.id.btn_a_note,
            R.id.btn_ab_note, R.id.btn_b_note})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_c_note:
                playSoundFromAssetMayWait("c");
                break;
            case R.id.btn_cd_note:
                playSoundFromAssetMayWait("cd");
                break;
            case R.id.btn_d_note:
                playSoundFromAssetMayWait("d");
                break;
            case R.id.btn_de_note:

                break;
            case R.id.btn_e_note:

                break;
            case R.id.btn_f_note:

                break;
            case R.id.btn_fg_note:

                break;
            case R.id.btn_g_note:
                break;
            case R.id.btn_ga_note:
                break;
            case R.id.btn_a_note:
                break;
            case R.id.btn_ab_note:
                break;
            case R.id.btn_b_note:
                break;
        }
    }

}
