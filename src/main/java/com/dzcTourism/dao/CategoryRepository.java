package com.dzcTourism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dzcTourism.domain.CommodityCategory;

public interface CategoryRepository extends JpaRepository<CommodityCategory, String>{

	/**
	 * 根据类别名称查找类别信息
	 * @param categoryName
	 * @return
	 */
	List<CommodityCategory> getByCategoryName(String categoryName);
}
