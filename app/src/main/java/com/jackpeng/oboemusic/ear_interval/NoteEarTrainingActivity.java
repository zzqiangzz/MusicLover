package com.jackpeng.oboemusic.ear_interval;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.jackpeng.lib_baselibrary.mvp.BaseActivity;
import com.jackpeng.lib_baselibrary.mvp.BaseEarActivity;
import com.jackpeng.oboemusic.R;
import com.jackpeng.oboemusic.utils.NoteRandomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteEarTrainingActivity extends BaseEarActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.btn_c_note)
    Button mCNote;
    @BindView(R.id.btn_cd_note)
    Button mCDNote;
    @BindView(R.id.btn_d_note)
    Button mDNote;
    @BindView(R.id.btn_de_note)
    Button mDENote;
    @BindView(R.id.btn_e_note)
    Button mENote;
    @BindView(R.id.btn_f_note)
    Button mFNote;
    @BindView(R.id.btn_fg_note)
    Button mFGNote;
    @BindView(R.id.btn_g_note)
    Button mGNote;
    @BindView(R.id.btn_ga_note)
    Button mGANote;
    @BindView(R.id.btn_a_note)
    Button mANote;
    @BindView(R.id.btn_ab_note)
    Button mABNote;
    @BindView(R.id.btn_b_note)
    Button mBNote;



    @BindView(R.id.sp_start_note)
    Spinner mSpStartNote;
    @BindView(R.id.sp_end_note)
    Spinner mSpEndNote;

    int startNoteIndex = 0;
    int endNoteIndex = 0;

    //默认音域：小字一组
    String startNoteStr = "c1";
    String endNoteStr = "b1";

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
        startNoteIndex = NoteRandomUtil.getIndexByNoteStr(startNoteStr);
        endNoteIndex = NoteRandomUtil.getIndexByNoteStr(endNoteStr);

        mSpStartNote.setSelection(startNoteIndex);
        mSpEndNote.setSelection(endNoteIndex);

        nextSoundListen();

        mSpStartNote.setOnItemSelectedListener(this);
        mSpEndNote.setOnItemSelectedListener(this);

    }

    @OnClick({R.id.btn_c_note, R.id.btn_cd_note, R.id.btn_d_note, R.id.btn_de_note, R.id.btn_e_note,
            R.id.btn_f_note, R.id.btn_fg_note, R.id.btn_g_note, R.id.btn_ga_note, R.id.btn_a_note,
            R.id.btn_ab_note, R.id.btn_b_note,R.id.btn_standard_note,R.id.btn_question_note,R.id.tv_select_note})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_standard_note:
                playSoundFromAssetMayWait("c1");
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
                    view.setSelected(true);
                    nextSoundListen();
                    T("答对了，下一题！");
                    //所有view都enable
                    enableAllButton();
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setSelected(false);
                        }
                    },500);
                } else {
                    //错了，先啥事不干
                    T("答错了，再听听~");
                    view.setEnabled(false);
                }
                break;
            case R.id.tv_select_note:
                showMultiChoiceDialog();
                break;
        }
    }

    private void enableAllButton() {
        mCNote.setEnabled(true);
        mCDNote.setEnabled(true);
        mDNote.setEnabled(true);
        mDENote.setEnabled(true);
        mENote.setEnabled(true);
        mFNote.setEnabled(true);
        mFGNote.setEnabled(true);
        mGNote.setEnabled(true);
        mGANote.setEnabled(true);
        mANote.setEnabled(true);
        mABNote.setEnabled(true);
        mBNote.setEnabled(true);
    }

    boolean[] yourChoice = new boolean[NoteRandomUtil.ALL_NOTE.length];
    private void showMultiChoiceDialog(){
        AlertDialog.Builder multiChoiceDialog =
                new AlertDialog.Builder(NoteEarTrainingActivity.this);
        multiChoiceDialog.setTitle("请选择要听辩的音阶");
        // 第二个参数是默认选项，此处设置为0
        multiChoiceDialog.setMultiChoiceItems(NoteRandomUtil.ALL_NOTE, yourChoice, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                yourChoice[which] = isChecked;
                T(which+" checked"+isChecked);
            }
        });
        multiChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //设置完成
                updateBtnShow();
            }
        });
        multiChoiceDialog.setNegativeButton("取消",null);
        multiChoiceDialog.setCancelable(true);
        multiChoiceDialog.show();
    }

    private void nextSoundListen(){
        //初始音并播放
        //currentNoteStr = NoteRandomUtil.getRandomNote();//这个方法直接给全85个音阶
        //currentNoteStr = NoteRandomUtil.getRandomNoteByRange(startNoteIndex,endNoteIndex);//这个方法限定音域,两参数位置可颠倒
        //这个方法限定音域,两参数位置可颠倒,而且指定音阶
        currentNoteStr = NoteRandomUtil.getRandomNoteByRangeWithNotes(startNoteIndex,endNoteIndex,selectNoteStr);
        playSoundFromAssetMayWait(currentNoteStr);
    }

    List<String> selectNoteStr = new ArrayList<>();
    private void updateBtnShow(){
        if (yourChoice.length == 12){
            if (yourChoice[0]){
                mCNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mCNote.getTag().toString());
            }else {
                mCNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mCNote.getTag().toString());
            }

            if (yourChoice[1]){
                mCDNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mCDNote.getTag().toString());
            }else {
                mCDNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mCDNote.getTag().toString());
            }

            if (yourChoice[2]){
                mDNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mDNote.getTag().toString());
            }else {
                mDNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mDNote.getTag().toString());
            }

            if (yourChoice[3]){
                mDENote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mDENote.getTag().toString());
            }else {
                mDENote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mDENote.getTag().toString());
            }

            if (yourChoice[4]){
                mENote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mENote.getTag().toString());
            }else {
                mENote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mENote.getTag().toString());
            }

            if (yourChoice[5]){
                mFNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mFNote.getTag().toString());
            }else {
                mFNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mFNote.getTag().toString());
            }

            if (yourChoice[6]){
                mFGNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mFGNote.getTag().toString());
            }else {
                mFGNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mFGNote.getTag().toString());
            }

            if (yourChoice[7]){
                mGNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mGNote.getTag().toString());
            }else {
                mGNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mGNote.getTag().toString());
            }

            if (yourChoice[8]){
                mGANote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mGANote.getTag().toString());
            }else {
                mGANote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mGANote.getTag().toString());
            }

            if (yourChoice[9]){
                mANote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mANote.getTag().toString());
            }else {
                mANote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mANote.getTag().toString());
            }

            if (yourChoice[10]){
                mABNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mABNote.getTag().toString());
            }else {
                mABNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mABNote.getTag().toString());
            }

            if (yourChoice[11]){
                mBNote.setVisibility(View.VISIBLE);
                selectNoteStr.add(mBNote.getTag().toString());
            }else {
                mBNote.setVisibility(View.INVISIBLE);
                selectNoteStr.remove(mBNote.getTag().toString());
            }
        }
    }


    private String currentNoteStr;
    private boolean checkResult(View view){
        if (view != null){
            String noteTag = (String)view.getTag();
            if (!TextUtils.isEmpty(currentNoteStr) && !TextUtils.isEmpty(noteTag)
                && NoteRandomUtil.getNoteByGroupNote(currentNoteStr).equals(noteTag)){
                return true;
            } else {
                //do nothing
            }
        } else {
            //do nothing
        }
        return false;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.sp_start_note:
                startNoteIndex = position;
                break;
            case R.id.sp_end_note:
                endNoteIndex = position;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
