package com.dzcTourism.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.CategoryRepository;
import com.dzcTourism.domain.CommodityCategory;

/**
 * 商品类别
 * @author wangzhitong
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//查询全部
	public List<CommodityCategory> getAll(){
		Sort sort=new Sort(Direction.DESC,"createTime");
		return categoryRepository.findAll(sort);
	}
	
	//根据名称查找类别
	public CommodityCategory getCategoryByName(String categoryName){
		List<CommodityCategory> category = categoryRepository.getByCategoryName(categoryName);
		return category!=null?category.get(0):null;
	}
	
	//根据ID查找类别
	public CommodityCategory getCategoryById(String id){
		return entityManager.find(CommodityCategory.class, id);
	} 
	
	//新增
	public void saveCategory(CommodityCategory category) {
		categoryRepository.save(category);
	}
	
	//编辑
	@Transactional
	public void updateCategory(CommodityCategory category) {
		entityManager.merge(category);
	}
	
	//删除
	public void deleteCategory(String id) {
		categoryRepository.delete(id);
	}
	
	//检查类别是否已存在
	public boolean checkIsExist(String categoryName) {
		List<CommodityCategory> category = categoryRepository.getByCategoryName(categoryName);
		if(category!=null && category.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
