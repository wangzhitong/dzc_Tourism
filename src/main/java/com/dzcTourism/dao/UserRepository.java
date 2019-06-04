package com.dzcTourism.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dzcTourism.domain.User;

/**
 * 	用户信息接口
 * @author wangzhitong
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

	User getByUserName(String userName);
	
	@Query("select u from User u where u.userName=?1 and u.password=?2")
	User login(String userName,String password);
}
