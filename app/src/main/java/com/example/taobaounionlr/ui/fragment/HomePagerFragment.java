package com.example.taobaounionlr.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
import com.example.taobaounionlr.model.domain.Categories;
import com.example.taobaounionlr.model.domain.HomePagerContent;
import com.example.taobaounionlr.presenter.ICategoryPagerPresenter;
import com.example.taobaounionlr.presenter.impl.CategoryPagePresenterImpl;
import com.example.taobaounionlr.utils.Constans;
import com.example.taobaounionlr.view.ICategoryPagerCallback;

import java.util.List;

public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresenter mCategoryPagerPresenter;

    public static HomePagerFragment newInstance(Categories.DataBean category){
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        //title和materialId 传过去
        bundle.putString(Constans.KEY_HOME_PAGER_TITLE,category.getTitle());
        bundle.putInt(Constans.KEY_HOME_PAGER_MATERIAL_ID,category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }
    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void initPresenter() {
        mCategoryPagerPresenter = CategoryPagePresenterImpl.getIntance();
        mCategoryPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constans.KEY_HOME_PAGER_TITLE);
        int materialId = arguments.getInt(Constans.KEY_HOME_PAGER_MATERIAL_ID);
        //TODO：加载数据
//        LogUtils.d(this,"title-->"+title);
//        Log.d("2020年9月8日17:37:24","materialId-->"+materialId);
//        LogUtils.d(this,"materialId-->"+materialId);
        if (mCategoryPagerPresenter != null){
            mCategoryPagerPresenter.getContentByCategoryId(materialId);
        }
    }

    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents) {

    }

    @Override
    public void onLoading(int categoryId) {

    }

    @Override
    public void onError(int categoryId) {

    }

    @Override
    public void onEmpty(int categoryId) {

    }

    @Override
    public void onLoaderMoreError(int categoryId) {

    }

    @Override
    public void onLoaderMoreEmpty(int categoryId) {

    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents) {

    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents) {

    }

    @Override
    protected void release() {
        if (mCategoryPagerPresenter != null){
            mCategoryPagerPresenter.unregisterViewCallback(this);
        }
    }
}
