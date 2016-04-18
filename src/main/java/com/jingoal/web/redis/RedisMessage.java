package com.jingoal.web.redis;

import java.io.Serializable;

import com.jingoal.web.common.Cacheable;
import com.jingoal.web.common.Message;

/**
 * @description: redis消息内容
 * 
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午12:44:16
 */
public abstract class RedisMessage<M extends Serializable> extends Message<M> implements Cacheable {

	/**
	 * serialVersionUID: 序列id
	 */
	private static final long serialVersionUID = 1L;

	// 消息处理失败，重试次数
	private int retry = 1;

	public RedisMessage(M m) {
		super(m);
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}
}
