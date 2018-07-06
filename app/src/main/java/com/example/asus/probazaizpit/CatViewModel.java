package com.example.asus.probazaizpit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CatViewModel extends AndroidViewModel {

    private CatRepository mRepository;

    private LiveData<List<Cat>> mData;

    public CatViewModel (@NonNull Application application) {
        super(application);
        mRepository = new CatRepository(application);
        mData = mRepository.getAllCats();
    }

    public LiveData<List<Cat>> getAllCats() {
        return mData;
    }

    public void insert(Cat cat) {
        mRepository.insert(cat);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }
}
