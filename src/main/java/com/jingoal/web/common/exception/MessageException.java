package com.jingoal.web.common.exception;

/**
 * @description: 发送消息异常
 * 
 *               <p>
 *               捕捉该异常后可能会重新发送
 *               </p>
 * 
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午12:34:39
 */
public class MessageException extends Exception {

	/**
	 * serialVersionUID: 序列id
	 */
	private static final long serialVersionUID = 1L;

	public MessageException() {
		super();
	}

	public MessageException(String msg) {
		super(msg);
	}

	public MessageException(Throwable e) {
		super(e);
	}

	public MessageException(String msg, Throwable e) {
		super(msg, e);
	}
}
