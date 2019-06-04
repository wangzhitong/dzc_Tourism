package com.dzcTourism.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.SceneryRepository;
import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.Scenery;

/**
 * 美景信息
 * @author wangzhitong
 *
 */
@Service
public class SceneryService {

	@Autowired
	private SceneryRepository sceneryRepository;
	
	/**
	 * 查询所有美景信息
	 * @return
	 */
	public List<Scenery> getAll(){
		Sort sort=new Sort(Direction.DESC,"createTime");
		return sceneryRepository.findAll(sort);
	}
	
	/**
	 * 新增美景信息
	 * @param food
	 */
	public void saveScenery(Scenery scenery) {
		sceneryRepository.save(scenery);
	}
	
	/**
	 * 根据ID查询对应的美景信息
	 * @param id
	 * @return
	 */
	public Scenery getSceneryById(String id) {
		return sceneryRepository.findOne(id);
	}
	
	
	/**
	 * 更新美景信息
	 * @param food
	 * @return
	 */
	public void updateScenery(Scenery scenery) {
		sceneryRepository.save(scenery);
	}
	
	/**
	 * 根据ID删除对应的信息
	 * @param id
	 */
	public void deleteScenery(String id) {
		sceneryRepository.delete(id);
	}

	@Transactional
	public void updateScenery(String title, String subheading, Date createTime, String content, String id) {
		sceneryRepository.updateScenery(title,subheading,createTime,content,id);
	}
	
	
	public boolean checkIsExist(String title) {
		List<Scenery> scenery = sceneryRepository.getByTitle(title);
		if(scenery!=null && scenery.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
