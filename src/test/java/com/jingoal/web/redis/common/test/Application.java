package com.jingoal.web.redis.common.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:root-context.xml");
		try {
			System.err.print("Redis Application Started!!!");
			Thread.sleep(999999999999999999L);
		} catch (InterruptedException e) {
			e.printStackTrace();
			context.close();
		}
	}

}
