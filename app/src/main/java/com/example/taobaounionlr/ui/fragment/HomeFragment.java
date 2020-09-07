package com.example.taobaounionlr.ui.fragment;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
import com.example.taobaounionlr.model.domain.Categories;
import com.example.taobaounionlr.presenter.impl.HomePresenterImpl;
import com.example.taobaounionlr.view.IHomeCallback;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private HomePresenterImpl mHomePresenter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        //加载presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);

    }

    @Override
    protected void loadData() {
        //加载数据 先准备presenter
        mHomePresenter.getCategories();

    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        //接口 加载的数据就会从这里回来
    }

    @Override
    protected void release() {
        //释放资源 取消回调注册
        if (mHomePresenter != null){
            mHomePresenter.unregisterCallback(this);
        }
    }
}
