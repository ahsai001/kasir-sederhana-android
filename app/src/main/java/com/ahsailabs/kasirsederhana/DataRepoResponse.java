package com.ahsailabs.kasirsederhana;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

/**
 * Created by ahmad s on 29/09/20.
 */
abstract class DataRepoResponse<T> {
    public MutableLiveData<List<T>> listLiveData = new MutableLiveData<>();
    public MutableLiveData<String> infoLiveData = new MutableLiveData<>();;
    public MutableLiveData<Integer> statusLiveData = new MutableLiveData<>();;
}
