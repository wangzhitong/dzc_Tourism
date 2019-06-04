package com.dzcTourism.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzcTourism.domain.FineFood;
import com.dzcTourism.domain.Msg;
import com.dzcTourism.service.FineFoodService;

@ResponseBody
@RequestMapping("/food")
@Controller
public class FineFoodController {

	@Autowired
	private FineFoodService fineFoodService;
	
//	@RequestMapping(value = "/getFood",method = RequestMethod.GET)
//	public Msg getFoodByName(String foodName) {
//		FineFood food = fineFoodService.getFoodByName(foodName);
//		return Msg.success().add("data", food);
//	}
	
	@RequestMapping(value = "/saveFood",method = RequestMethod.POST)
	public Msg saveFood(@RequestBody @Valid FineFood food,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			System.out.println(food);
			fineFoodService.saveFood(food);
			return Msg.success();
		}
	}
	
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public Msg getFoodAll() {
		List<FineFood> foodAll = fineFoodService.getFoodAll();
		return Msg.success().add("data", foodAll);
	}
	
	@RequestMapping(value = "/delFood",method = RequestMethod.GET)
	public Msg delFood(String id) {
		fineFoodService.deleteFood(id);
		return Msg.success();
	}
	
	@RequestMapping(value = "/updateFood",method = RequestMethod.POST)
	public Msg updateFood(@RequestBody FineFood food) {
		if(food != null && food.getId() != null) {
			String id = food.getId();
			FineFood oldFood = fineFoodService.getFoodById(id);
			if(oldFood != null) {
				oldFood.setFoodName(food.getFoodName());
				oldFood.setPicture1(food.getPicture1());
				oldFood.setPicture2(food.getPicture2());
				oldFood.setPicture3(food.getPicture3());
				oldFood.setPicture4(food.getPicture4());
				oldFood.setPicture5(food.getPicture5());
				oldFood.setBusiness_id(food.getBusiness_id());
				oldFood.setCategory(food.getCategory());
				oldFood.setDescription(food.getDescription());
				fineFoodService.updateFood(oldFood);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}else {
			return Msg.fail();
		}
	}
	
}
