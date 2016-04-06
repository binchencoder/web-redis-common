package com.jingoal.web.redis.sample.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:root-context.xml")
public class Redis4ListOperationsTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private RedisTemplate<String, String> template; // inject the template as
													// ListOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	@Test
	public void addLink() {
		listOps.leftPush("a", "b");
	}

}
