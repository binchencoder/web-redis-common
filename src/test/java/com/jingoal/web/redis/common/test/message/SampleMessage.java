package com.jingoal.web.redis.common.test.message;

import com.jingoal.web.redis.RedisMessage;

public class SampleMessage extends RedisMessage<String> {

	private static final long serialVersionUID = 1L;

	public SampleMessage(String m) {
		super(m);
	}

	/**
	 * 消息缓存或持久化的时长,默认一月
	 * 
	 * @return 时长为秒
	 */
	public int expire() {
		return 25920000; // 一月
	}

	/**
	 * 在Redis中的Key, 也是队列名称
	 * 
	 * @return 队列名称
	 */
	public String key() {
		return "chenbin.message";
	}

}
