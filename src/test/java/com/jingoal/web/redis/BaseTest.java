package com.jingoal.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:root-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

  @Autowired
  public RedisTemplate<String, String> template; // inject the template as
                                                 // ListOperations

}
