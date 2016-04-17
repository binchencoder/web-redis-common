package com.jingoal.web.redis;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.jingoal.web.common.exception.MessageException;
import com.jingoal.web.common.queue.Producer;

/**
 * @description: redis消息发送实现
 * 
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @since 1.0.0
 * @time: 2016年4月15日 下午12:43:05
 */
public class RedisProducer<M extends Serializable> implements Producer<RedisMessage<M>> {

	private static final Logger logger = LoggerFactory.getLogger(RedisProducer.class);
	
	private JedisConnectionFactory connectionFactory;
	private RedisSerializer<Object> serializer;
	
	public void setConnectionFactory(JedisConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void setSerializer(RedisSerializer<Object> serializer) {
		this.serializer = serializer;
	}

	public void send(RedisMessage<M> message) throws MessageException {
		JedisConnection jedisConnection = null;
		try {
			jedisConnection = connectionFactory.getConnection();
			byte[] key = serializer.serialize(message.key());
			byte[] value = serializer.serialize(message);
			jedisConnection.lPush(key, value);
			jedisConnection.expire(key, message.expire());
		} catch (Exception ex) {
			logger.error("error[key=" + message.key() + " seconds=" + message.expire() + "]" + ex.getMessage(), ex);
		} finally {
			jedisConnection.close();
		}
	}
}
