package com.jackpeng.lib_baselibrary.mvp;

public interface IPresenter<V> {
    void attachView(V view);
    void detachView();
}
