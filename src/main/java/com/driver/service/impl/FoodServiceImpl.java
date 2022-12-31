package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {

        return null;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {

        return null;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {

        return null;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {

    }

    @Override
    public List<FoodDto> getFoods() {


        return null;
    }
}