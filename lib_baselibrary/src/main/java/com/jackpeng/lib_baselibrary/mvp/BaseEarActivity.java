package com.jackpeng.lib_baselibrary.mvp;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jackpeng.lib_baselibrary.R;

import java.io.IOException;
import java.util.HashMap;

import static android.media.AudioManager.STREAM_MUSIC;

public abstract class BaseEarActivity<T extends BasePresenter> extends BaseActivity implements SoundPool.OnLoadCompleteListener {
    protected SoundPool mSoundPool = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载声音
        initSoundPool();
    }

    private boolean isReady = false;
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        if (mSoundPool != null) {
            D("load finish");
            isReady = true;
        }
    }

    private void initSoundPool() {
        //设置描述音频流信息的属性
        AudioAttributes abs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            abs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(100)   //设置允许同时播放的流的最大值
                    .setAudioAttributes(abs)   //完全可以设置为null
                    .build() ;
        } else {
            /**
             * maxStreams:允许同时播放的流的最大值
             * streamType ：音频流的类型描述
             * srcQuality：采样率转化质量
             */
            mSoundPool = new SoundPool(100, STREAM_MUSIC, 0);
        }
        mSoundPool.setOnLoadCompleteListener(this);  // 设置加载完成监听
        loadSound();
    }

    HashMap<String, Integer> sounddata = new HashMap<>();
    private void loadSound(){
        sounddata.put("c",mSoundPool.load(mContext, R.raw.c,1));
        sounddata.put("cd",mSoundPool.load(mContext, R.raw.cd,1));
        sounddata.put("d",mSoundPool.load(mContext, R.raw.d,1));
        sounddata.put("de",mSoundPool.load(mContext, R.raw.de,1));
        sounddata.put("e",mSoundPool.load(mContext, R.raw.e,1));
        sounddata.put("f",mSoundPool.load(mContext, R.raw.f,1));
        sounddata.put("fg",mSoundPool.load(mContext, R.raw.fg,1));
        sounddata.put("g",mSoundPool.load(mContext, R.raw.g,1));
        sounddata.put("ga",mSoundPool.load(mContext, R.raw.ga,1));
        sounddata.put("a",mSoundPool.load(mContext, R.raw.a,1));
        //sounddata.put("ab",mSoundPool.load(mContext, R.raw.ab,1));
        //sounddata.put("b",mSoundPool.load(mContext, R.raw.b,1));
    }

    //从asset中播放指定名字声音
    public void playSoundFromAssetMayWait(String name) {
        if (mSoundPool == null || !isReady){
            T("稍等，音符还在赶来的路上~");
            return;
        }
        D("name is"+name);
        if (sounddata.containsKey(name)){
            mSoundPool.play(sounddata.get(name),
                    1,// 左声道音量
                    1,// 右声道音量
                    1, // 优先级
                    0,// 循环播放次数
                    1);// 回放速度，该值在0.5-2.0之间 1为正常速度
        } else {
            //提示该音符还未添加
            T("该音符还未添加~");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSoundPool.release();
    }
}
