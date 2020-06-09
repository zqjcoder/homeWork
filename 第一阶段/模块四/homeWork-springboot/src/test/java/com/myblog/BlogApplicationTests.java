package com.myblog;

import com.myblog.controller.IndexController;
import com.myblog.entity.Article;
import com.myblog.mapper.ArticleMapper;
import com.myblog.pojo.PageRequest;
import com.myblog.pojo.PageResult;
import com.myblog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private IndexController indexController;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private ArticleService articleService;

	@Test
	void contextLoads() {
	}

	@Test
	void mapperTest() {
		Article byId = articleMapper.findById(1);
		System.out.println(byId);
	}

	@Test
	void articleServiceTest() {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNum(1);
		pageRequest.setPageSize(2);
		PageResult page = articleService.findPage(pageRequest);
		System.out.println(page);
	}

}
