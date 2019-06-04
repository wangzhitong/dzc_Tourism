package com.dzcTourism.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.dzcTourism.domain.Entertainment;
import com.dzcTourism.domain.Msg;
import com.dzcTourism.service.EntertainmentService;
import com.dzcTourism.util.StrUtils;

/**
 * 游玩娱乐信息
 * @author wangzhitong
 *
 */
@RequestMapping("/enter")
@RestController
public class EntertainmentController {

	@Autowired
	private EntertainmentService entertainmentService;
	
	
	@GetMapping(value = "/getAll")
	public Msg getAll() {
		List<Entertainment> enterAll = entertainmentService.getAll();
		
		return Msg.success().add("data", enterAll);
	}
	
	@GetMapping("/getEnterById")
	public Msg getEntertainmentById(String id) {
		Entertainment entertainment = entertainmentService.getEntertainmentById(id);
		return Msg.success().add("entertainment", entertainment);
	}
	
	@GetMapping(value = "/delEnter")
	public Msg deleteEntertainment(String id) {
		entertainmentService.deleteEntertainment(id);
		return	Msg.success();
	}
	
	
	@PostMapping("/save")
	public Msg saveEntertainment(@RequestBody @Valid Entertainment entertainment,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			entertainment.setSubheading("");
			entertainment.setSubheading(StrUtils.extractContent(entertainment.getContent()));
			entertainmentService.saveEntertainment(entertainment);
			return	Msg.success();
		}
	}
	
	@PostMapping("/updateEnter")
	public Msg updateEnter(@RequestBody Entertainment entertainment) {
		if(entertainment != null && entertainment.getId() != null) {
			String id = entertainment.getId();
			Entertainment oldEnter = entertainmentService.getEntertainmentById(id);
			if(oldEnter != null) {
				String title = entertainment.getTitle();
				String subheading = StrUtils.extractContent(entertainment.getContent());
				Date createTime = entertainment.getCreateTime();
				String content = entertainment.getContent();
				entertainmentService.updateEnter(title,subheading,createTime,content,id);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}else {
			return Msg.fail();
		}
	}
	
	//验证
	@RequestMapping(value = "/checking",method = RequestMethod.GET)
	public Msg checking(String title) {
		boolean checkIsExist = entertainmentService.checkIsExist(title);
		if(checkIsExist) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "名称已存在");
		}
	}
}
