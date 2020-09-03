package com.example.taobaounionlr.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taobaounionlr.R;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return loadRootView(inflater,container,savedInstanceState);
        //return inflater.inflate(R.layout.fragment_home,container,false);
    }

    protected  View loadRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        需要个id,让子类返回
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);//显示lyout也在父类做了
    }

    protected abstract int getRootViewResId();//子类只要返回一个layout的id
}
