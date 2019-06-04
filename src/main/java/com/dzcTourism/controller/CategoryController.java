package com.dzcTourism.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dzcTourism.domain.CommodityCategory;
import com.dzcTourism.domain.Msg;
import com.dzcTourism.service.CategoryService;


/**
 * 商品类别信息
 * @author wangzhitong
 *
 */
@RequestMapping("/category")
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public Msg getAll() {
		List<CommodityCategory> categoryAll = categoryService.getAll();
		return Msg.success().add("data", categoryAll);
	}

	@RequestMapping(value = "/getByName",method = RequestMethod.GET)
	public Msg getByName(String categoryName) {
		CommodityCategory category = categoryService.getCategoryByName(categoryName);
		return Msg.success().add("data", category);
	}
	
	@RequestMapping(value = "/saveCategory",method = RequestMethod.POST)
	public Msg saveCategory(@RequestBody @Valid CommodityCategory category,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			categoryService.saveCategory(category);
			return	Msg.success();
		}
	}
	
	@RequestMapping(value = "/updateCategory",method = RequestMethod.POST)
	public Msg updateCategory(@RequestBody CommodityCategory category) {
		if(category != null && category.getId().length()>0) {
			String id = category.getId();
			CommodityCategory oldCategory = categoryService.getCategoryById(id);
			if(oldCategory!=null) {
				oldCategory.setCategoryName(category.getCategoryName());
				oldCategory.setRemark(category.getRemark());
				categoryService.updateCategory(oldCategory);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}
		return Msg.fail();
	}
	
	@RequestMapping(value = "/deleteCategory",method = RequestMethod.GET)
	public Msg deleteCategory(String id) {
		categoryService.deleteCategory(id);
		return Msg.success();
	}

	@RequestMapping(value = "/checking",method = RequestMethod.GET)
	public Msg checking(String categoryName) {
		boolean checkIsExist = categoryService.checkIsExist(categoryName);
		if(checkIsExist) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "名称已存在");
		}
	}
}
