package com.dzcTourism.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.dzcTourism.domain.Msg;
import com.dzcTourism.domain.News;
import com.dzcTourism.service.NewsService;
import com.dzcTourism.util.StrUtils;

/**
 * 	新闻
 * @author LU
 *
 */
@ResponseBody
@RequestMapping("/news")
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	

	@GetMapping("/getAll")
	public Msg getAll() {
		List<News> newsAll = newsService.getAll();

		return Msg.success().add("data", newsAll);
	}
	
	@GetMapping("/getNewsById")
	public Msg getNewsById(String id) {
		News news = newsService.getNewsById(id);
		return Msg.success().add("news", news);
	}
	
	@GetMapping(value = "/delNews")
	public Msg deleteNews(String id) {
		newsService.deleteNews(id);
		return	Msg.success();
	}
	
	
	@PostMapping("/save")
	public Msg saveNews(@RequestBody @Valid News news,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			news.setSubheading("");
			news.setSubheading(StrUtils.extractContent(news.getContent()));
			newsService.saveNews(news);
			return	Msg.success();
		}
	}

	
	@PostMapping("/updateNews")
	public Msg updateNews(@RequestBody News news) {
		if(news != null && news.getId() != null) {
			String id = news.getId();
			News oldNews = newsService.getNewsById(id);
			if(oldNews != null) {
				String title = news.getTitle();
				String subheading = StrUtils.extractContent(news.getContent());
				Date createTime = news.getCreateTime();
				String content = news.getContent();
				newsService.updateNews(title,subheading,createTime,content,id);
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
		
		boolean checkIsExist = newsService.checkIsExist(title);
		if(checkIsExist) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "标题已存在");
		}
	}
}
