package com.example.h2oh2o;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BonusViewModel extends ViewModel {
    private final MutableLiveData<Integer> bonusPoints = new MutableLiveData<>(0);

    public static void addBonusPoints(int bonusPoints) {
    }

    public LiveData<Integer> getBonusPoints() {
        return bonusPoints;
    }


    
}
