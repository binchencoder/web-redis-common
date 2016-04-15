package com.jingoal.web.redis;

import java.io.Serializable;

import com.jingoal.web.common.Keyable;
import com.jingoal.web.common.Message;
import com.jingoal.web.common.queue.MessageListener;


public interface RedisMessageListener<T extends Serializable> extends Keyable, MessageListener<Message<T>> {
	
}
