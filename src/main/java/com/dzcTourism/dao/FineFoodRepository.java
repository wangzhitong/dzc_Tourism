package com.dzcTourism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.FineFood;

/**
 * 美食
 * @author 王志统
 *
 */
public interface FineFoodRepository extends JpaRepository<FineFood, String>{

	//根据美食名称获取美食信息
//	@Query("select b,u")
	List<Business> getByFoodName(String foodName);
}
