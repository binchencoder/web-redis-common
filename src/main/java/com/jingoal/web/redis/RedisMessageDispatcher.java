package com.jingoal.web.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.jingoal.web.hessian.HessianSerializer;

/**
 * @description: redis 消息队列消息分发器
 *
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午2:12:18
 */
public class RedisMessageDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(RedisProducer.class);

	private RedisMessageDispatcher() {
	}

	private static class DispatcherHolder {
		private static RedisMessageDispatcher instance;
	}

	public static RedisMessageDispatcher getInstance() {
		return DispatcherHolder.instance;
	}

	private JedisConnectionFactory connectionFactory;
	private Map<String, RedisMessageListener<String>> listeners = new HashMap<String, RedisMessageListener<String>>();
	private Executor executor = Executors.newCachedThreadPool(); // 默认线程池
	private RedisSerializer<Object> serializer = new HessianSerializer(); // 默认序列化类
	private volatile boolean isRunning = true;

	public void setConnectionFactory(JedisConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void addListener(RedisMessageListener<String> listener) {
		this.listeners.put(listener.key(), listener);
	}

	public void addListeners(List<RedisMessageListener<String>> listenerList) {
		for (RedisMessageListener<String> listener : listenerList) {
			this.addListener(listener);
		}
	}

	public void setListeners(List<RedisMessageListener<String>> listenerList) {
		this.listeners.clear();
		this.addListeners(listenerList);
	}

	public void setSerializer(RedisSerializer<Object> serializer) {
		this.serializer = serializer;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	@SuppressWarnings("unchecked")
	public void start() {
		logger.info("redis message dispatcher starting......");

		for (final Map.Entry<String, RedisMessageListener<String>> entry : listeners.entrySet()) {
			executor.execute(new Runnable() {
				public void run() {
					JedisConnection jedisConnection = null;
					try {
						jedisConnection = connectionFactory.getConnection();
						logger.info("listening queue destination {} ......", entry.getKey());
						while (isRunning) {
							byte[] key = serializer.serialize(entry.getKey());
							byte[] value = jedisConnection.rPop(key);
							if (value != null) {
								entry.getValue().onMessage((RedisMessage<String>) serializer.deserialize(value));
							}
						}
					} catch (Exception ex) {
						logger.error("error[key=" + entry.getKey() + "]" + ex.getMessage(), ex);
					} finally {
						RedisConnectionUtils.releaseConnection(jedisConnection, connectionFactory);
					}
				}
			});
		}
	}

	public void shutdown() {
		this.isRunning = false;
	}
}
