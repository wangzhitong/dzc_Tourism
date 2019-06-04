package com.dzcTourism.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.FineFoodRepository;
import com.dzcTourism.domain.FineFood;

@Service
public class FineFoodService {

	@Autowired
	private FineFoodRepository fineFoodRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	//
//	public FineFood getFoodByName(String foodName) {
//		return fineFoodRepository.getByFoodName(foodName);
//	}
	
	public FineFood getFoodById(String id) {
		return entityManager.find(FineFood.class, id);
	}
	
	//保存
	public void saveFood(FineFood food) {
		fineFoodRepository.save(food);
	}
	
	//查找全部
	public List<FineFood> getFoodAll(){
		return fineFoodRepository.findAll();
	}
	
	//删除
	public void deleteFood(String id) {
		fineFoodRepository.delete(id);
	}
	
	//更新
	@Transactional
	public void updateFood(FineFood food) {
		entityManager.merge(food);
	}
}
