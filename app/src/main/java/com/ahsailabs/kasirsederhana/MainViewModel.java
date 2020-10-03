package com.ahsailabs.kasirsederhana;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

/**
 * Created by ahmad s on 28/09/20.
 */
public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;
    private CategoryRepoResponse categoryRepoResponse;
    private MenuRepoResponse menuRepoResponse;

    private SavedStateHandle savedStateHandle;

    public MainViewModel(SavedStateHandle savedStateHandle){
        this.savedStateHandle = savedStateHandle;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public CategoryRepoResponse loadCategoryData() {
        if(categoryRepoResponse == null) {
            categoryRepoResponse = mainRepository.loadCategoryData();
        }
        return categoryRepoResponse;
    }

    public MenuRepoResponse loadMenuData() {
        if(menuRepoResponse == null) {
            menuRepoResponse = mainRepository.loadMenuData();
        }
        return menuRepoResponse;
    }
}
