package com.jackpeng.lib_baselibrary.mvp;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jackpeng.lib_baselibrary.R;

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
            //T("音符已就位，随时听候您的指示~");
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
        sounddata.put("xc1",mSoundPool.load(mContext, R.raw.xc1,1));
        sounddata.put("xcd1",mSoundPool.load(mContext, R.raw.xcd1,1));
        sounddata.put("xd1",mSoundPool.load(mContext, R.raw.xd1,1));
        sounddata.put("xde1",mSoundPool.load(mContext, R.raw.xde1,1));
        sounddata.put("xe1",mSoundPool.load(mContext, R.raw.xe1,1));
        sounddata.put("xf1",mSoundPool.load(mContext, R.raw.xf1,1));
        sounddata.put("xfg1",mSoundPool.load(mContext, R.raw.xfg1,1));
        sounddata.put("xg1",mSoundPool.load(mContext, R.raw.xg1,1));
        sounddata.put("xga1",mSoundPool.load(mContext, R.raw.xga1,1));
        sounddata.put("xa1",mSoundPool.load(mContext, R.raw.xa1,1));
        //sounddata.put("xab",mSoundPool.load(mContext, R.raw.ab,1));
        //sounddata.put("xb",mSoundPool.load(mContext, R.raw.b,1));
        sounddata.put("c1",mSoundPool.load(mContext, R.raw.c1,1));
        sounddata.put("cd1",mSoundPool.load(mContext, R.raw.cd1,1));
        sounddata.put("d1",mSoundPool.load(mContext, R.raw.d1,1));
        sounddata.put("de1",mSoundPool.load(mContext, R.raw.de1,1));
        sounddata.put("e1",mSoundPool.load(mContext, R.raw.e1,1));
        sounddata.put("f1",mSoundPool.load(mContext, R.raw.f1,1));
        //sounddata.put("fg1",mSoundPool.load(mContext, R.raw.xfg1,1));
        sounddata.put("g1",mSoundPool.load(mContext, R.raw.g1,1));
        sounddata.put("ga1",mSoundPool.load(mContext, R.raw.ga1,1));
        sounddata.put("a1",mSoundPool.load(mContext, R.raw.a1,1));
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
