package com.jackpeng.lib_baselibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jackpeng.lib_baselibrary.utils.TUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends Activity {
    private final String TAG ="MusicLover:";

    public P mvpPresenter;
    protected Context mContext;
    protected Activity mActivity;
    //黄油刀
    Unbinder binder;

    //布局
    public abstract int getLayoutId();
    //初始化
    public abstract void initViews(Bundle savedInstanceState);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        binder = ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        mvpPresenter = TUtil.getT(this, 0);
        if (this instanceof IBaseView) {
            mvpPresenter.attachView(this);
        }
        initViews(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑黄油刀
        if (binder != null) {
            binder.unbind();
        }
        //解绑p
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    protected Toast mToast;
    public void T(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                    mToast.show();
                } else {
                    mToast.setText(msg);
                    mToast.show();
                }
            }
        });
    }

    public void D(final String msg){
        Log.d(TAG,msg);
    }

    public void E(final String msg){
        Log.e(TAG,msg);
    }

    public void showProgressBar(String title, String message){

    }

    public void resultOK() {
        setResult(RESULT_OK);
        finish();
    }

    public void resultCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * 获取页面context
     *
     * @return
     */
    public Context getThisContext() {
        return this;
    }

    public Activity getThisActivity() {
        return this;
    }
}
