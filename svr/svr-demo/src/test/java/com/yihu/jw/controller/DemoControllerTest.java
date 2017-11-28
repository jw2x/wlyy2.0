package com.yihu.jw.controller;  //目录要和web的目录一致

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chenweida
 * 目录要和web的目录一致
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages={"com"})
//@FixMethodOrder(MethodSorters.DEFAULT)   //默认顺序由方法名hashcode值来决定，如果hash值大小一致，则按名字的字典顺序确定 由于hashcode的生成和操作系统相关(以native修饰），所以对于不同操作系统，可能会出现不一样的执行顺序，在某一操作系统上，多次执行的顺序不变
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //按照方法名字排序升序
//单元测试事物回滚
@Transactional
@Rollback //默认true即回滚，false不回滚
public class DemoControllerTest  {
	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	/**
	 * 初始化模拟mvc环境
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testAll() throws Exception {
		whenQuerySuccess1();
		whenQuerySuccess2();
	}

	/**
	 * 测试查询
	 * @throws Exception
     */
	//@Ignore指定忽略此测试方法
	public void whenQuerySuccess2() throws Exception {
		String result = mockMvc.perform(
				get("/demo")
						// .param("size", "15")
						// .param("page", "3")
						// .param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
		System.out.println("2222222222222222222222222222");
	}
	/**
	 * 测试查询
	 * @throws Exception
	 */
	//@Test
	//@Ignore指定忽略此测试方法
	public void whenQuerySuccess1() throws Exception {
		String result = mockMvc.perform(
				get("/demo")
						// .param("size", "15")
						// .param("page", "3")
						// .param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println(result);
		System.out.println("111111111111111111111111111");
	}
}
