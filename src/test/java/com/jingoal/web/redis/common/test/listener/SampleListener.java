package com.jingoal.web.redis.common.test.listener;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingoal.web.common.exception.MessageException;
import com.jingoal.web.redis.RedisMessage;
import com.jingoal.web.redis.RedisMessageListener;
import com.jingoal.web.redis.RedisProducer;

@Component
public class SampleListener implements RedisMessageListener<Serializable> {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory.getLogger(SampleListener.class);

  @Autowired
  private RedisProducer<Serializable> redisProducer;

  @Override
  public String key() {
    return "chenbin.message";
  }

  @Override
  public void onMessage(RedisMessage<Serializable> message) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    logger.info("onMessage");
    System.out.println("come in SampleListener, retry : " + message.getRetry());

    if (message.getRetry() == 3) {
      // 记录
      return;
    }

    try {
      System.err.println(message.getParams());
      System.err.println(message.getPayload());

      throw new Exception();
    } catch (Exception e) {
      if (message.getRetry() < 3) { // 重试3次后失败，将失败数据写入到临时库中
        message.setRetry(message.getRetry() + 1);
        try {
          redisProducer.send(message);
        } catch (MessageException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
