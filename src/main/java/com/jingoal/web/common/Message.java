package com.jingoal.web.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 发送消息体
 *
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午12:36:49
 */
public class Message<T extends Serializable> implements Serializable {

	/**
	 * serialVersionUID: 序列id
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> params = new HashMap<String, Object>();
	private final T payload;

	public Message(T t) {
		this.payload = t;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public T getPayload() {
		return payload;
	}
}
