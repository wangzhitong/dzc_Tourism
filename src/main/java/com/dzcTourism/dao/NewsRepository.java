package com.dzcTourism.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dzcTourism.domain.News;

/**
 * 新闻信息接口
 * @author wangzhitong
 *
 */
public interface NewsRepository extends JpaRepository<News, String> {

	@Modifying
	@Query("update News n set n.title=?1,n.subheading=?2,n.createTime=?3,n.content=?4 where n.id=?5")
	void updateNews(String title,String subheading,Date createTime,String content,String id);
	
	List<News> getByTitle(String title);
}
