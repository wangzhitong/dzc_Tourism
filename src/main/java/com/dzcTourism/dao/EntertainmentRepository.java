package com.dzcTourism.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dzcTourism.domain.Entertainment;

/**
 *	游玩娱乐信息接口
 * @author wangzhitong
 *
 */
public interface EntertainmentRepository extends JpaRepository<Entertainment, String> {

	@Modifying
	@Query("update Entertainment e set e.title=?1,e.subheading=?2,e.createTime=?3,e.content=?4 where e.id=?5")
	void updateEnter(String title, String subheading, Date createTime, String content, String id);
	
	/**
	 * 根据标题查找游玩娱乐信息
	 * @param title
	 * @return
	 */
	List<Entertainment> getByTitle(String title);
}
