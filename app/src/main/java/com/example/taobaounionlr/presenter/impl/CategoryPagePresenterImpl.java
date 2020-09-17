package com.example.taobaounionlr.presenter.impl;

import com.example.taobaounionlr.model.Api;
import com.example.taobaounionlr.model.domain.HomePagerContent;
import com.example.taobaounionlr.presenter.ICategoryPagerPresenter;
import com.example.taobaounionlr.utils.LogUtils;
import com.example.taobaounionlr.utils.RetrofitManager;
import com.example.taobaounionlr.utils.UrlUtils;
import com.example.taobaounionlr.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagePresenterImpl implements ICategoryPagerPresenter {
    private Map<Integer,Integer> pagersInfo = new HashMap<>();

    public static final int DEFAULT_PAGE = 1;

    //单例 home里每个tab对应一个数据接口
    private CategoryPagePresenterImpl(){

    }

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getIntance(){
        if (sInstance == null){
            sInstance = new CategoryPagePresenterImpl();
        }
        return sInstance;
    }

    @Override
    public void getContentByCategoryId(int categoryId) {
        //loading
        for (ICategoryPagerCallback callback : callbacks) {
            callback.onLoading(categoryId);
        }
        //根据分类id去加载内容
        //TODO：
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Integer targetPage = pagersInfo.get(categoryId);
        if (targetPage == null){
            targetPage = DEFAULT_PAGE;
            pagersInfo.put(categoryId,targetPage                        );
        }
        String homePagerUrl = UrlUtils.createHomePagerUrl(categoryId, targetPage);
        LogUtils.d(CategoryPagePresenterImpl.this,"home pager url -->" + homePagerUrl);
        Call<HomePagerContent> task = api.getHomePageContent(homePagerUrl);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                int code = response.code();
                LogUtils.d(CategoryPagePresenterImpl.this,"code -->"+code);
                if (code == HttpURLConnection.HTTP_OK){
                    HomePagerContent pagerContent = response.body();
                    LogUtils.d(CategoryPagePresenterImpl.this,"pageContent-->"+pagerContent);
                    //把数据给到UI更新
                    handleHomePageContentResult(pagerContent,categoryId);
                }else {
                    handleNetworkError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this,"onFailure-->"+t.toString());
            }
        });
    }

    //网络错误
    private void handleNetworkError(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryId() == categoryId){
                callback.onError(categoryId);
            }
        }
    }

    private void handleHomePageContentResult(HomePagerContent pagerContent, int categoryId) {
        //通知UI层更新数据
        for (ICategoryPagerCallback callback : callbacks){
            if (pagerContent == null || pagerContent.getData().size() == 0){
                callback.onEmpty(categoryId);
            }else {
                callback.onContentLoaded(pagerContent.getData(),categoryId);
            }  
        }
    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    //集合
    private List<ICategoryPagerCallback> callbacks = new ArrayList<>();

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {
        if (!callbacks.contains(callback)){
            callbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {
        callbacks.remove(callback);
    }
}
