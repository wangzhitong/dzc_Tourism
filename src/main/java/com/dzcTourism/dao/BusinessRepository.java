package com.dzcTourism.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dzcTourism.domain.Business;

/**
 * 	商家信息接口
 * @author wangzhitong
 *
 */
public interface BusinessRepository extends JpaRepository<Business, String>,JpaSpecificationExecutor<Business>  {

//	@Modifying
//	@Query("update Business b set b.businessName=?1,b.businessInfo=?2,b.createTime=?3,b.content=?4 where b.id=?5")
//	void updateBusiness(String businessName, String businessInfo, Date createTime, String content, String id);

	/**
	 * 按商家分类查找
	 * @param businessType
	 * @return
	 */
	@Query("select b from Business b where b.businessType like %?1%")
	Business getBusinessByType(String businessType);
	
	/**
	 * 根据商家名称查找商家信息
	 * @param businessName
	 * @return
	 */
	List<Business> getByBusinessName(String businessName);
}
