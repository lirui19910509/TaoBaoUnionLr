package com.example.taobaounionlr.presenter;

import com.example.taobaounionlr.view.IHomeCallback;

public interface IHomePresenter {
    /**
     *获取商品分类category
     * */
    void getCategories();

    /**
     * 注册UI通知接口
     *
     * @param callback
     * */
    void registerCallback(IHomeCallback callback);

    /**
     * 取消UI通知的接口
     *
     * @param callback
     * */
    void unregisterCallback(IHomeCallback callback);
}
