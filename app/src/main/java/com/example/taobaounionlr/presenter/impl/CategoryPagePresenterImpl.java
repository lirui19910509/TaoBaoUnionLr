package com.example.taobaounionlr.presenter.impl;

import com.example.taobaounionlr.model.Api;
import com.example.taobaounionlr.model.domain.HomePagerContent;
import com.example.taobaounionlr.presenter.ICategoryPagerPresenter;
import com.example.taobaounionlr.utils.LogUtils;
import com.example.taobaounionlr.utils.RetrofitManager;
import com.example.taobaounionlr.utils.UrlUtils;
import com.example.taobaounionlr.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.HashMap;
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
                }else {
                    //TODO:
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this,"onFailure-->"+t.toString());
            }
        });
    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {

    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {

    }
}
