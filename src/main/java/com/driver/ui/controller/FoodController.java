package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodService foodService;

	@Autowired
	FoodRepository foodRepository;
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		foodService.getFoodById(id);
		return null;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto foodDto = FoodDto.builder().foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory())
				.foodName(foodDetails.getFoodName()).build();
		foodService.createFood(foodDto);

		FoodEntity foodEntity = foodRepository.findByFoodName(foodDetails.getFoodName());
		FoodDetailsResponse response = FoodDetailsResponse.builder().foodId(foodEntity.getFoodId()).
				foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory())
				.foodName(foodDetails.getFoodName()).build();

		return  response;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodEntity food = foodRepository.findByFoodId(id);
		food.setFoodCategory(foodDetails.getFoodCategory());
		food.setFoodName(foodDetails.getFoodName());
		food.setFoodPrice(foodDetails.getFoodPrice());

		FoodDetailsResponse response = FoodDetailsResponse.builder().foodId(food.getFoodId()).
				foodPrice(food.getFoodPrice()).foodCategory(food.getFoodCategory())
				.foodName(food.getFoodName()).build();
		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel OSM = new OperationStatusModel();
		OSM.setOperationName(String.valueOf(RequestOperationName.DELETE));
		OSM.setOperationResult(String.valueOf(RequestOperationStatus.ERROR));

		FoodEntity food = foodRepository.findByFoodId(id);

		if(food != null) {
			foodService.deleteFoodItem(id);
			OSM.setOperationResult(String.valueOf(RequestOperationStatus.SUCCESS));
		}
		return OSM;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtos = foodService.getFoods();
		List<FoodDetailsResponse> ansList = new ArrayList<>();

		for(FoodDto X: foodDtos) {
			FoodDetailsResponse response = FoodDetailsResponse.builder().foodId(X.getFoodId()).
					foodPrice(X.getFoodPrice()).foodCategory(X.getFoodCategory())
					.foodName(X.getFoodName()).build();

			ansList.add(response);
		}
		return ansList;
	}
}
