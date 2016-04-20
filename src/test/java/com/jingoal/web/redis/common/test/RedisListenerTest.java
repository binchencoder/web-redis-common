package com.jingoal.web.redis.common.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.jingoal.web.common.exception.MessageException;
import com.jingoal.web.redis.BaseTest;
import com.jingoal.web.redis.RedisProducer;
import com.jingoal.web.redis.common.test.message.SampleMessage;

public class RedisListenerTest extends BaseTest {

	@Autowired
	private RedisProducer<String> redisProducer;

	@Test
	public void sendMsgTest() {
		SampleMessage message = new SampleMessage("aaaaaaa");

		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		message.setParams(map);

		for (int i = 0; i < 10; i++) {
			try {
				redisProducer.send(message);
			} catch (MessageException e) {
				e.printStackTrace();
			}
		}

		/*
		 * for (int i = 0; i < 10; i++) { try { redisProducer.send(message); }
		 * catch (MessageException e) { e.printStackTrace(); } }
		 */
	}

}
