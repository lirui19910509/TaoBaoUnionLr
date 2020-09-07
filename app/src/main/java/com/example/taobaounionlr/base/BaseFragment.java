package com.example.taobaounionlr.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container,savedInstanceState);//先加载View
        initPresenter();//创建presenter
        loadData();//加载数据 抽象方法，子类必须实现
        return rootView ;
        //return inflater.inflate(R.layout.fragment_home,container,false);
    }

    //取消注册
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    protected void release(){
        //释放资源
    }

    protected void initPresenter(){
        //创建presenter

    }

    protected  void loadData(){
        //加载数据
    }


    protected  View loadRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        需要个id,让子类返回
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);//显示lyout也在父类做了
    }

    protected abstract int getRootViewResId();//子类只要返回一个layout的id
}
