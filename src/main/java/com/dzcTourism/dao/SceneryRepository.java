package com.dzcTourism.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dzcTourism.domain.Scenery;

/**
 * 美景介绍信息接口
 * @author wangzhitong
 *
 */
public interface SceneryRepository extends JpaRepository<Scenery, String> {

	@Modifying
	@Query("update Scenery s set s.title=?1,s.subheading=?2,s.createTime=?3,s.content=?4 where s.id=?5")
	void updateScenery(String title, String subheading, Date createTime, String content, String id);

	List<Scenery> getByTitle(String title);
}
