package com.apress.prospring4.ch4.profile.kindergarten;

import com.apress.prospring4.ch4.profile.Food;
import com.apress.prospring4.ch4.profile.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServicelmpl implements FoodProviderService {
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<Food>();
        lunchSet.add(new Food("Milk"));
        lunchSet.add(new Food("Biscuits"));
        return lunchSet;
    }
}
