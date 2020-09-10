package com.example.taobaounionlr.presenter;

import com.example.taobaounionlr.base.IBasePresenter;
import com.example.taobaounionlr.view.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    /**
     *获取商品分类category
     * */
    void getCategories();
}
