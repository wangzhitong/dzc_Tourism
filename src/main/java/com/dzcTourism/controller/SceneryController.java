package com.dzcTourism.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dzcTourism.domain.Msg;
import com.dzcTourism.domain.Scenery;
import com.dzcTourism.service.SceneryService;
import com.dzcTourism.util.StrUtils;

/**
 *	 美景
 * @author wangzhitong
 *
 */
@RequestMapping("/scenery")
@Controller
public class SceneryController {

	@Autowired
	private SceneryService sceneryService;
	
	@ResponseBody
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public Msg getAll() {
		List<Scenery> sceneryAll = sceneryService.getAll();
		return Msg.success().add("data", sceneryAll);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSceneryById",method = RequestMethod.GET)
	public Msg getSceneryById(String id) {
		Scenery scenery = sceneryService.getSceneryById(id);
		return Msg.success().add("scenery", scenery);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delScenery",method = RequestMethod.GET)
	public Msg deleteScenery(String id) {
		sceneryService.deleteScenery(id);
		return	Msg.success();
	}
	
	/*
	 * @RequestBody 在一个请求方法中只能有一个，ajax请求体只有一个
	 * ajax请求的写法 contentType:"application/json charset=utf-8",
	 */
	@RequestMapping(value = "/save1",method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile multipartFile,
		 HttpServletRequest request, HttpServletResponse response,RedirectAttributes model) {
		//通过file.getInputStream();处理
		System.out.println(multipartFile.getOriginalFilename());
		if(multipartFile != null) {
			String fileName = multipartFile.getOriginalFilename();
			String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/img";
			File file = new File(filePath,fileName);
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			try {
				multipartFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String strPath = filePath+"/"+fileName;
			System.out.println(strPath);
			model.addAttribute("path", strPath);
		}
		return "redirect:save";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Msg saveScenery(@ModelAttribute(value = "path") String path,@RequestBody @Valid Scenery scenery,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			scenery.setHomePic(path);
			sceneryService.saveScenery(scenery);
			return	Msg.success();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updateScenery",method = RequestMethod.POST)
	public Msg updateScenery(@RequestBody Scenery scenery) {
		if(scenery != null && scenery.getId() != null) {
			String id = scenery.getId();
			Scenery oldScenery = sceneryService.getSceneryById(id);
			if(oldScenery != null) {
				String title = scenery.getTitle();
				String subheading = StrUtils.extractContent(scenery.getContent());
				Date createTime = scenery.getCreateTime();
				String content = scenery.getContent();
				sceneryService.updateScenery(title,subheading,createTime,content,id);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}else {
			return Msg.fail();
		}
	}
	
	//验证
	@ResponseBody
	@RequestMapping(value = "/checking",method = RequestMethod.GET)
	public Msg checking(String title) {
		boolean checkIsExist = sceneryService.checkIsExist(title);
		if(checkIsExist) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "名称已存在");
		}
	}
}
