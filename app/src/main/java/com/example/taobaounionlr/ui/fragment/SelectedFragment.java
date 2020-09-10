package com.example.taobaounionlr.ui.fragment;

import android.view.View;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;

/**
 * 精选
 * */

public class SelectedFragment extends BaseFragment {

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_selected;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
