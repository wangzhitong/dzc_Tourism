package com.dzcTourism.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.BusinessRepository;
import com.dzcTourism.domain.Business;

/**
 *	商家信息
 * @author wangzhitong
 *
 */
@Service
public class BusinessService {
	
	@Autowired
	private BusinessRepository businessRepository;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 查询所有商家信息
	 * @return
	 */
	public List<Business> getAll(){
		Sort sort=new Sort(Direction.DESC,"createTime");
		return businessRepository.findAll(sort);
	}
	
	/**
	 * 新增商家信息
	 * @param business
	 */
	public void saveBusiness(Business business) {
		businessRepository.save(business);
	}
	
	/**
	 * 根据ID查询对应的商家信息
	 * @param id
	 * @return
	 */
	public Business getBusinessById(String id) {
		return entityManager.find(Business.class, id);
	}
	
	
	/**
	 * 更新商家信息
	 * @param business
	 * @return
	 */
//	public void updateBusiness(Business business) {
//		businessRepository.save(business);
//	}
	
	/**
	 * 根据ID删除对应的信息
	 * @param id
	 */
	public void deleteBusiness(String id) {
		businessRepository.delete(id);
	}

	/**
	 * 	更新商家信息
	 * @param businessName
	 * @param businessInfo
	 * @param createTime
	 * @param content
	 * @param id
	 */
	@Transactional
	public void updateBusiness(Business business) {
			// String businessName, String businessInfo, Date createTime, String content, String id) {
		//businessRepository.updateBusiness(businessName,businessInfo,createTime,content,id);
		entityManager.merge(business);
	}
	
	/**
	 * 根据商家分类查找商家
	 * @param typeName
	 * @return
	 */
	public Business getBusinessByType(String typeName) {
		return businessRepository.getBusinessByType(typeName);
	}
	
	/**
	 *	根据商品名称查找包含商品的所有店家信息
	 * @param foodName
	 * @return
	 */
	public List<Business> searchFood(String foodName){
		
		List<Business> list = new ArrayList<Business>();
		String sql = "select business_id from dzc_finefood where food_Name like '%"+ foodName +"%'";
		Query query = entityManager.createNativeQuery(sql);
		List resultList = query.getResultList();
		for(int i=0;  i<resultList.size(); i++) {
			Business business = getBusinessById(String.valueOf(resultList.get(i)));
			list.add(business);
		}
		return list;
	}
	
	/**
	 * 根据商品分类查找商品信息
	 * @param categoryName
	 * @return
	 */
	public List<Business> getFoodByCategory(String categoryName){
		
		List<Business> list = new ArrayList<Business>();
		String sql = "select business_id from dzc_finefood where category = '"+ categoryName +"' GROUP BY business_id";
		Query query = entityManager.createNativeQuery(sql);
		List resultList = query.getResultList();
		for(int i=0;  i<resultList.size(); i++) {
			Business business = getBusinessById(String.valueOf(resultList.get(i)));
			list.add(business);
		}
		return list;
	}
	
	/**
	 * 检查用户名是否可用
	 */
	public boolean checkIsExist(String businessName) {
		List<Business> business = businessRepository.getByBusinessName(businessName);
		if(business!=null && business.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
