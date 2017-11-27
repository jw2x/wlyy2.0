package com.yihu.jw.controller;  //目录要和web的目录一致

import com.yihu.jw.common.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chenweida
 * 目录要和web的目录一致
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest extends BaseTest {
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

	/**
	 * 测试查询
	 * @throws Exception
     */
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(
				get("/demo")
						// .param("size", "15")
						// .param("page", "3")
						// .param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}

}
