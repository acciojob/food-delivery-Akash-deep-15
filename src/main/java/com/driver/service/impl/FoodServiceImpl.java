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
        FoodEntity foodEntity = FoodEntity.builder().foodId(food.getFoodId()).foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory()).foodPrice(food.getFoodPrice()).build();
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = FoodDto.builder().foodId(foodEntity.getFoodId()).foodName(foodEntity.getFoodName())
                .foodCategory(foodEntity.getFoodCategory()).foodPrice(foodEntity.getFoodPrice()).build();
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {

        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        foodEntity = FoodEntity.builder().foodId(foodDetails.getFoodId()).foodName(foodDetails.getFoodName())
                .foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory()).build();

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

        List<FoodEntity> foodList = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtos = new ArrayList<>();

        for(FoodEntity x: foodList) {
            FoodDto foodDto = FoodDto.builder().foodId(x.getFoodId()).foodName(x.getFoodName())
                    .foodPrice(x.getFoodPrice()).foodCategory(x.getFoodCategory()).build();

            foodDtos.add(foodDto);
        }
        return foodDtos;
    }
}