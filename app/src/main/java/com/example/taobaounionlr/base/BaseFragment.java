package com.example.taobaounionlr.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private State currentState = State.NONE;
    private View mSuccessView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public enum State {
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }

    private Unbinder mBind;
    private FrameLayout mBaseContainer;

    @OnClick(R.id.network_error_tips)
    public void retry(){
        //点击了重新加载内容
        LogUtils.d(this,"on retry...");
        onRetryClick();
    }

    /**
     * 如果子fragment需要知道网络错误以后的点击，那覆盖方法即可
     * */
    protected void onRetryClick(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //View rootView = inflater.inflate(R.layout.base_fragment_layout,container,false);
        View rootView = loadRootView(inflater,container);
//找到填坑的坑
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        mBind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initPresenter();//创建presenter
        loadData();//加载数据 抽象方法，子类必须实现
        return rootView ;
        //return inflater.inflate(R.layout.fragment_home,container,false);
    }

    //特殊情况在homeFragment里复写改方法
    protected View loadRootView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    /**
     * 加载各种状态的View
     * @param inflater
     * @param container
     * */
    protected  void loadStatesView(LayoutInflater inflater, ViewGroup container){
        //成功的View
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        //Loading的View
        mLoadingView = loadLoadingView(inflater,container);
        mBaseContainer.addView(mLoadingView);

        //加载错误页面
        mErrorView = loadErrorView(inflater,container);
        mBaseContainer.addView(mErrorView);

        //加载空页面
        mEmptyView = loadEmptyView(inflater,container);
        mBaseContainer.addView(mEmptyView);

        setUpState(State.NONE);

    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_error,container,false);
    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }

    /**
     * 子类通过这个方法切换状态
     * @param state
     * */
    //暴露一个方法 用于切换
    public void setUpState(State state){
        this.currentState = state;
        //控制显示与隐藏
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE :View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);

    }

    /**
     * 加载loading界面 可复写
     * @param inflater
     * @param container
     * @param
     * */
    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container){
      return   inflater.inflate(R.layout.fragment_loading,container,false);
    }

    protected  void initView(View rootView){

    }

    //取消注册
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null){
            mBind.unbind();
        }
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


    protected  View loadSuccessView(LayoutInflater inflater, ViewGroup container){
//        需要个id,让子类返回
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);//显示lyout也在父类做了
    }

    protected abstract int getRootViewResId();//子类只要返回一个layout的id
}
