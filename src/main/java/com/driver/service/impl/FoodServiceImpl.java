package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(food.getId());
        foodEntity.setFoodId(food.getFoodId());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodPrice(food.getFoodPrice());
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {

        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = FoodDto.builder().id(foodEntity.getId())
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodCategory(foodEntity.getFoodCategory())
                .foodPrice(foodEntity.getFoodPrice()).build();
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        foodEntity.setId(foodDetails.getId());
        foodEntity.setFoodId(foodDetails.getFoodId());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodRepository.save(foodEntity);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> list = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> ansList = new ArrayList<>();

        for(FoodEntity x: list) {
            FoodDto foodDto = FoodDto.builder().id(x.getId())
                    .foodId(x.getFoodId())
                    .foodName(x.getFoodName())
                    .foodCategory(x.getFoodCategory())
                    .foodPrice(x.getFoodPrice()).build();

            ansList.add(foodDto);
        }
        return ansList;
    }
}