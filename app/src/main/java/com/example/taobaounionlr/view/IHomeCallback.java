package com.example.taobaounionlr.view;

import com.example.taobaounionlr.model.domain.Categories;

public interface IHomeCallback {
    void onCategoriesLoaded(Categories categories);

    void onNetworkError();

    void onLoading();

    void onEmpty();
}
