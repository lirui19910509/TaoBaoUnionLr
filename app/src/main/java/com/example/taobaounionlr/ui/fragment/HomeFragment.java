package com.example.taobaounionlr.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
import com.example.taobaounionlr.model.domain.Categories;
import com.example.taobaounionlr.presenter.impl.HomePresenterImpl;
import com.example.taobaounionlr.ui.adapter.HomePagerAdapter;
import com.example.taobaounionlr.utils.LogUtils;
import com.example.taobaounionlr.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {
    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;
    @BindView(R.id.home_pager)
    public ViewPager homePager;

    private HomePresenterImpl mHomePresenter;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        //加载presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerViewCallback(this);

    }

    //复写该方法
    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragment_layout,container,false);
    }

    @Override
    protected void initView(View rootView) {
        //初始化各种控件 tab绑定viewpage
        mTabLayout.setupWithViewPager(homePager);
        //给ViewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        //刷新 设置适配器
        homePager.setAdapter(mHomePagerAdapter);

    }

    @Override
    protected void loadData() {
        //setUpState(State.LOADING);
        //加载数据 先准备presenter
        mHomePresenter.getCategories();

    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        //判断不在这判断 全部交给逻辑层
//        if (categories == null || categories.getData().size() == 0){
//            setUpState(State.EMPTY);
//        } else {
//            setUpState(State.SUCCESS);
//        }
        setUpState(State.SUCCESS);
        //接口 加载的数据就会从这里回来
        LogUtils.d(this,"onCategotiesLoaded..");
        if (mHomePagerAdapter != null){
            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    public void onNetworkError() {
        setUpState(State.ERROR);
        
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    protected void release() {
        //释放资源 取消回调注册
        if (mHomePresenter != null){
            mHomePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void onRetryClick() {
        //网络错误，点击了重试
        //重新加载分类内容
        if (mHomePresenter != null){
            mHomePresenter.getCategories();
        }
    }
}
