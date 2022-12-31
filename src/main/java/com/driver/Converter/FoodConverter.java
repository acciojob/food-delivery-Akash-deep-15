package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {

    public static FoodEntity requestToEntity(FoodDetailsRequestModel request) {
        return FoodEntity.builder().
                foodName(request.getFoodName())
                .foodPrice(request.getFoodPrice())
                .foodCategory(request.getFoodCategory())
                .build();
    }

    public static FoodDetailsResponse entityToResponse(FoodEntity foodEntity) {
        return FoodDetailsResponse.builder()
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodPrice(foodEntity.getFoodPrice())
                .foodCategory(foodEntity.getFoodCategory())
                .build();
    }
}
