package com.example.taobaounionlr.ui.fragment;

import android.view.View;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
/**
 * 特惠
 * */

public class RedPacketFragment extends BaseFragment {

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_red_packet;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
