package com.dzcTourism.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.support.json.JSONUtils;
import com.dzcTourism.App;
import com.dzcTourism.domain.Business;
import com.dzcTourism.domain.Msg;
import com.dzcTourism.domain.News;
import com.dzcTourism.domain.User;
import com.dzcTourism.service.BusinessService;
import com.dzcTourism.service.NewsService;
import com.dzcTourism.service.UserService;
import com.dzcTourism.util.StrUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class TestDAO {
	
	@Autowired
	private	BusinessService businessService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserService user;
	
	
//	@Before
//	public void testCreateDatebase() {
//		
//	}

	@Test
	public void testSave() {
		
//		Business business = new Business();
//		business.setId(null);
//		business.getBusinessName("到站村美食asd");
//	//	business.setFoodName("不翻汤asd");
//		business.setContent("<a href='www.baidu.com'></>");
//		
//		foodService.saveFood(business);
	}
	
	@Test
	public void testUpdate() {
		
//		Business business = new Business();
//		business.setId("402800816ae27da2016ae27dabb40000");
//		business.setTitle("到站村美食美食美食");
//		business.setBusinessInfo("羊肉串");
//		business.setCreateTime(new Date());
//		business.setContent("<a href='www.baidu.com'></>");
//		
//		foodService.updateFood(business);
	}
	
	@Test
	public void testFindAll() {
		List<Business> foodList = businessService.getAll();
		System.out.println(foodList.size());
	}
	
	@Test
	public void testDel() {
		businessService.deleteBusiness("402800816ae2818e016ae28197180000");
	}
	
	
	
	@Test
	public void testSaveNews() {
		
		News news = new News();
		news.setId(null);
		news.setTitle("到站村美食新闻");
		news.setCreateTime(new Date());
		news.setContent("<a href='www.baidu.com'></>");
		
		newsService.saveNews(news);
	}
	
	@Test
	public void testSearch() {
		List<Business> searchFood = businessService.searchFood("糖");
		System.out.println(searchFood);
	}
	
	@Test
	public void testLogin() {
		boolean login = user.login("admins", "a123456");
		System.out.println(login);
	}
	
	@Test
	public void testCheck() {
		boolean checkIsExist = businessService.checkIsExist("干果小点");
		System.out.println(checkIsExist);
	}
	@Test
	public void testStr() {
//		String str = "java怎么把asdasd字符串中的545456556的汉字取出来";
//		String reg = "[^\u4e00-\u9fa50-9]";
//
//		str = str.replaceAll(reg, "");
//		System.out.println(str);
		
		String str1 = "<h1 style=\"font-weight:normal;font-size:20px;text-align:center;color:#333333;font-family:微软雅黑, &quot;\">\r\n" + 
				"	<br />\r\n" + 
				"</h1>\r\n" + 
				"<div class=\"cont\" style=\"margin:0px;padding:0px;color:#333333;font-family:微软雅黑, &quot;font-size:15px;\">\r\n" + 
				"	<p>\r\n" + 
				"		昨天下午，洛阳市伊滨经开区在商会大厦A座五楼中会议室召开文明诚信经营创建工作推进会，区管委会副主任张宛澍，区宣传统战办主任尚明亮，区市场监督管理局负责人董震，区工商分局副局长杜李君出席会议，各镇负责宣传工作的副职及相关负责同志参加会议。\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		< img src=\"http://lyjj.akfh.cn:81/daozhan/images/news2.jpg\" alt=\"倒盏村荣获洛阳市文明诚信经营示范市场称号\" style=\"height:auto;\" />\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		会上表彰了全区51家2018年洛阳市级、区级文明诚信经营示范市场、文明诚信经营示范企业、文明诚信经营示范商户，并举行了授牌仪式。\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		< img src=\"http://lyjj.akfh.cn:81/daozhan/images/news2-2.jpg\" alt=\"倒盏村荣获洛阳市文明诚信经营示范市场称号\" style=\"height:auto;\" />\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		倒盏村民俗市场被授予2018年度洛阳市文明诚信经营示范市场荣誉称号，倒盏村十家商户荣获2018年度洛阳市文明诚信经营示范商户称号，二十家商户荣获2018年度伊滨区文明诚信经营示范商户称号。\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		< img src=\"http://lyjj.akfh.cn:81/daozhan/images/news2-3.jpg\" alt=\"倒盏村荣获洛阳市文明诚信经营示范市场称号\" style=\"height:auto;\" />\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		洛阳美丽老家旅游发展有限公司总经理韩波，洛阳万茗堂电子商务有限公司负责人，李村镇晓飞羊骨汤烩面馆负责人分别代表受表彰的市场、企业和商户做了发言。\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		< img src=\"http://lyjj.akfh.cn:81/daozhan/images/news2-4.jpg\" alt=\"倒盏村荣获洛阳市文明诚信经营示范市场称号\" style=\"height:auto;\" />\r\n" + 
				"	</p >\r\n" + 
				"	<p>\r\n" + 
				"		会上宣读了中共洛阳市委宣传部、洛阳市精神文明建设指导委员会办公室、洛阳市市场监督管理局关于表彰2018年度洛阳市“文明诚信经营示范市场 文明诚信经营示范企业 文明诚信经营示范商户”的决定，伊滨经开区管委会副主任张宛澍做了重要讲话。\r\n" + 
				"	</p >\r\n" + 
				"</div>";
		System.out.println(StrUtils.removeHtmlTag(str1));
	}
	
	@Test
	public void testStringToJson() throws  IOException {
		String str = "{\"title\":\"大萨达啊\",\"subheading\":\"\",\"content\":\"撒大声地\",\"createTime\":\"2019-06-04 10:39:59\",\"multipartFile\":\"{}\",\"homePic\":\"\"}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode readTree = mapper.readTree(str);
		JsonNode jsonNode = readTree.get("title");
		System.out.println(jsonNode.asText());
	}
}
