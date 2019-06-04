package com.dzcTourism.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.FineFood;
import com.dzcTourism.domain.Msg;
import com.dzcTourism.service.BusinessService;

/**
 * 	商家信息
 * @author LU
 *
 */
@RequestMapping("/business")
@RestController
public class BusinessCntroller {

	@Autowired
	private BusinessService businessService;

	
	@GetMapping(value = "/getAll")
	public Msg getAll() {
		List<Business> businessAll = businessService.getAll();
		return Msg.success().add("data", businessAll);
	}
	
	@GetMapping("/getBusinessById")
	public Msg getEntertainmentById(String id) {
		Business business = businessService.getBusinessById(id);
		return Msg.success().add("business", business);
	}
	
	@GetMapping(value = "/delBusiness")
	public Msg deleteEntertainment(String id) {
		businessService.deleteBusiness(id);
		return	Msg.success();
	}
	
	
	@PostMapping("/save")
	public Msg saveEntertainment(@RequestBody @Valid Business business,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			businessService.saveBusiness(business);
			return	Msg.success();
		}
	}
	
	@PostMapping("/updateBusiness")
	public Msg updateBusiness(@RequestBody Business business) {
		if(business != null && business.getId() != null) {
			String id = business.getId();
			Business oldBusiness = businessService.getBusinessById(id);
			if(oldBusiness != null) {
				oldBusiness.setId(business.getId());
				oldBusiness.setBusinessName(business.getBusinessName());
				oldBusiness.setBusinessInfo(business.getBusinessInfo());
				oldBusiness.setCreateTime(business.getCreateTime());
				oldBusiness.setContent(business.getContent());
				oldBusiness.setBusinessType(business.getBusinessType());
				List<FineFood> foodInfo = business.getFoodInfo();
				for(FineFood f : foodInfo) {
					f.setBusiness_id(id);
				}
				oldBusiness.setFoodInfo(foodInfo);
				businessService.updateBusiness(oldBusiness);
				
//				String businessName = business.getBusinessName();
//				String businessInfo = business.getBusinessInfo();
//				Date createTime = business.getCreateTime();
//				String content = business.getContent();
//				businessService.updateBusiness(businessName,businessInfo,createTime,content,id);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}else {
			return Msg.fail();
		}
	}
	
	@RequestMapping(value = "/getBusinessType",method = RequestMethod.GET)
	public Msg getBusinessTypeName(String typeName) {
		Business business = businessService.getBusinessByType(typeName);
		return Msg.success().add("data", business);
	}
	

	/*
	 * 根据商品名称查找店家信息
	 * @param foodName
	 * @return
	 */
	@RequestMapping(value = "/foodSearch",method = RequestMethod.GET)
	public Msg foodSearch(String foodName) {
		List<Business> business = businessService.searchFood(foodName);
		return Msg.success().add("data", business);
	}
	
	//根据商品分类查找商品信息
	@RequestMapping(value="/getFoodByCategory",method = RequestMethod.GET)
	public Msg getFoodByCategory(String id) {
		List<Business> foodList = businessService.getFoodByCategory(id);
		return Msg.success().add("data", foodList);
	}
	
	//验证
	@RequestMapping(value = "/checking",method = RequestMethod.GET)
	public Msg checking(String businessName) {
		boolean checkIsExist = businessService.checkIsExist(businessName);
		if(checkIsExist) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "商家名称已存在");
		}
	}

}
