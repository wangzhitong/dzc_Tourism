package com.dzcTourism.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.NewsRepository;
import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.News;

/**
 * 新闻信息
 * @author wangzhitong
 *
 */
@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	/**
	 * 查询所有新闻信息
	 * @return
	 */
	public List<News> getAll(){
		
		Sort sort=new Sort(Direction.DESC,"createTime");
		return newsRepository.findAll(sort);
	}
	
	/**
	 * 新增新闻信息
	 * @param food
	 */
	public void saveNews(News news) {
		newsRepository.save(news);
	}
	
	/**
	 * 根据ID查询对应的新闻信息
	 * @param id
	 * @return
	 */
	public News getNewsById(String id) {
		return newsRepository.findOne(id);
	}
	
	
	/**
	 * 更新新闻信息
	 * @param food
	 * @return
	 */
	@Transactional
	public void updateNews(String title,String subheading,Date createTime,String content,String id) {
		newsRepository.updateNews(title, subheading, createTime, content, id);
	}
	
	
	/**
	 * 根据ID删除对应的信息
	 * @param id
	 */
	public void deleteNews(String id) {
		newsRepository.delete(id);
	} 
	
	public boolean checkIsExist(String title) {
		List<News> news = newsRepository.getByTitle(title);
		if(news!=null && news.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
