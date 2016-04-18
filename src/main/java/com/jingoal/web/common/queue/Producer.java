package com.jingoal.web.common.queue;

import java.io.Serializable;

import com.jingoal.web.common.exception.MessageException;

/**
 * @description: 消息发送接口
 * 
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午12:38:09
 */
public interface Producer<T extends Serializable> {

	/**
	 * @description: 发送消息
	 *
	 * @param message
	 * @throws MessageException
	 * 
	 * @author: chenbin
	 * @since: 1.0.0
	 * @time: 2016年4月15日 下午12:38:30
	 */
	public void send(T message) throws MessageException;
}
