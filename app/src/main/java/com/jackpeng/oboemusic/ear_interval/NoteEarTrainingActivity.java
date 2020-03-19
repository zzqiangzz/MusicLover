package com.jackpeng.oboemusic.ear_interval;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.jackpeng.lib_baselibrary.mvp.BaseActivity;
import com.jackpeng.lib_baselibrary.mvp.BaseEarActivity;
import com.jackpeng.oboemusic.R;
import com.jackpeng.oboemusic.utils.NoteRandomUtil;

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
        nextSoundListen();
    }

    @OnClick({R.id.btn_c_note, R.id.btn_cd_note, R.id.btn_d_note, R.id.btn_de_note, R.id.btn_e_note,
            R.id.btn_f_note, R.id.btn_fg_note, R.id.btn_g_note, R.id.btn_ga_note, R.id.btn_a_note,
            R.id.btn_ab_note, R.id.btn_b_note,R.id.btn_standard_note,R.id.btn_question_note})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_standard_note:
                playSoundFromAssetMayWait("c");
                break;
            case R.id.btn_question_note:
                playSoundFromAssetMayWait(currentNoteStr);
                break;
            case R.id.btn_c_note:
            case R.id.btn_cd_note:
            case R.id.btn_d_note:
            case R.id.btn_de_note:
            case R.id.btn_e_note:
            case R.id.btn_f_note:
            case R.id.btn_fg_note:
            case R.id.btn_g_note:
            case R.id.btn_ga_note:
            case R.id.btn_a_note:
            case R.id.btn_ab_note:
            case R.id.btn_b_note:
                if (checkResult(view)){
                    //对了，播下一题
                    nextSoundListen();
                    T("答对了，下一题！");
                } else {
                    //错了，先啥事不干
                    T("答错了，继续听听哈~");
                }
                break;
        }
    }

    private void nextSoundListen(){
        //初始音并播放
        currentNoteStr = NoteRandomUtil.getRandomNote();
        playSoundFromAssetMayWait(currentNoteStr);
    }


    private String currentNoteStr;
    private boolean checkResult(View view){
        if (view != null){
            String noteTag = (String)view.getTag();
            if (!TextUtils.isEmpty(currentNoteStr) && !TextUtils.isEmpty(noteTag)
                && currentNoteStr.equals(noteTag)){
                return true;
            } else {
                //do nothing
            }
        } else {
            //do nothing
        }
        return false;
    }

}
