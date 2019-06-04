package com.dzcTourism.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.EntertainmentRepository;
import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.Entertainment;
import com.dzcTourism.domain.Scenery;

/**
 * 游玩娱乐信息
 * @author wangzhitong
 *
 */
@Service
public class EntertainmentService {

	@Autowired
	private EntertainmentRepository entertainmentRepository;
	
	/**
	 * 查询所有游玩娱乐信息
	 * @return
	 */
	public List<Entertainment> getAll(){
		return entertainmentRepository.findAll();
	}
	
	/**
	 * 新增游玩娱乐信息
	 * @param food
	 */
	public void saveEntertainment(Entertainment entertainment) {
		entertainmentRepository.save(entertainment);
	}
	
	/**
	 * 根据ID查询对应的游玩娱乐信息
	 * @param id
	 * @return
	 */
	public Entertainment getEntertainmentById(String id) {
		return entertainmentRepository.findOne(id);
	}
	
	
	/**
	 * 更新游玩娱乐信息
	 * @param food
	 * @return
	 */
	public void updateEntertainment(Entertainment entertainment) {
		entertainmentRepository.save(entertainment);
	}
	
	/**
	 * 根据ID删除对应的信息
	 * @param id
	 */
	public void deleteEntertainment(String id) {
		entertainmentRepository.delete(id);
	}

	@Transactional
	public void updateEnter(String title, String subheading, Date createTime, String content, String id) {
		entertainmentRepository.updateEnter(title,subheading,createTime,content,id);
	} 
	
	
	public boolean checkIsExist(String title) {
		List<Entertainment> entertainment = entertainmentRepository.getByTitle(title);
		if(entertainment!=null && entertainment.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
