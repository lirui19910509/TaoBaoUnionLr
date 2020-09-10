package com.example.taobaounionlr.ui.fragment;

import android.view.View;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
/**
 *搜索
 * */
public class SearchFragment extends BaseFragment {

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
