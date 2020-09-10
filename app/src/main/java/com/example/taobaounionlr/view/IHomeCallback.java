package com.example.taobaounionlr.view;

import com.example.taobaounionlr.base.IBaseCallback;
import com.example.taobaounionlr.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {
    void onCategoriesLoaded(Categories categories);
}
