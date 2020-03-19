package com.jackpeng.lib_baselibrary.mvp;

public class BasePresenter<V> implements IPresenter<V>{
    public V mvpView;

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }
}
