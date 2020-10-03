package com.ahsailabs.kasirsederhana;

import android.view.Menu;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.ahsailabs.kasirsederhana.configs.Config;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryItem;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryResponse;
import com.ahsailabs.kasirsederhana.ui.menu.models.MenuResponse;
import com.ahsailabs.kasirsederhana.ui.menu.test.Response;
import com.ahsailabs.kasirsederhana.utils.HttpUtil;
import com.ahsailabs.kasirsederhana.utils.InfoUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by ahmad s on 29/09/20.
 */
public class MainRepository implements LifecycleObserver {
    private OkHttpClient okHttpClient;
    Lifecycle lifecycle;

    public MainRepository(OkHttpClient okHttpClient, Lifecycle lifecycle){
        this.okHttpClient = okHttpClient;
        this.lifecycle = lifecycle;
        lifecycle.addObserver(this);
    }

    public CategoryRepoResponse loadCategoryData() {
        CategoryRepoResponse categoryRepoResponse = new CategoryRepoResponse();
        AndroidNetworking.get(Config.getCategoryListUrl())
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .setTag("category-list")
                .build()
                .getAsObject(CategoryResponse.class, new ParsedRequestListener<CategoryResponse>() {
                    @Override
                    public void onResponse(CategoryResponse response) {
                        if (response.getStatus() == 1) {
                            categoryRepoResponse.listLiveData.setValue(response.getData());
                        } else {
                            categoryRepoResponse.infoLiveData.setValue(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        categoryRepoResponse.infoLiveData.setValue(anError.getMessage());
                        categoryRepoResponse.statusLiveData.setValue(anError.getErrorCode());
                    }
                });

        return categoryRepoResponse;

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy(){
        AndroidNetworking.cancelAll();
    }

    public MenuRepoResponse loadMenuData() {
        MenuRepoResponse menuRepoResponse = new MenuRepoResponse();
        AndroidNetworking.get(Config.getMenuListUrl())
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .setTag("menu-list")
                .build()
                .getAsObject(MenuResponse.class, new ParsedRequestListener<MenuResponse>() {
                    @Override
                    public void onResponse(MenuResponse response) {
                        if (response.getStatus() == 1) {
                            menuRepoResponse.listLiveData.setValue(response.getData());
                        } else {
                            menuRepoResponse.infoLiveData.setValue(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        menuRepoResponse.infoLiveData.setValue(anError.getMessage());
                        menuRepoResponse.statusLiveData.setValue(anError.getErrorCode());
                    }
                });

        return menuRepoResponse;
    }
}
