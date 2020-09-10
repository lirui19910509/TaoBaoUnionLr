package com.example.taobaounionlr.presenter;

import com.example.taobaounionlr.base.IBasePresenter;
import com.example.taobaounionlr.view.ICategoryPagerCallback;

public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {
    /**
     * 根据分类id去获取内容
     * @param categoryId
     * */
    void getContentByCategoryId (int categoryId);

    void loaderMore(int categoryId);

    void reload(int categoryId);
}
