package com.jingoal.web.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * Description: Hessian 序列化实现
 * 
 * @company: 北京今目标信息技术有限公司
 */
public class HessianSerializer implements RedisSerializer<Object> {

  private static final Logger logger = LoggerFactory.getLogger(HessianSerializer.class);

  public byte[] serialize(Object object) {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    Hessian2Output ho = new Hessian2Output(os);
    try {
      ho.writeObject(object);
    } catch (IOException e) {
      logger.error("HessianSerializer serialize error:{}", e);
    } finally {
      try {
        os.flush();
        ho.flush();
      } catch (Exception ex) {
        logger.error("HessianSerializer serialize flush error:{}", ex);
      }
    }

    return os.toByteArray();
  }

  public Object deserialize(byte[] bytes) {
    ByteArrayInputStream is = new ByteArrayInputStream(bytes);
    Hessian2Input hi = new Hessian2Input(is);
    try {
      return hi.readObject();
    } catch (IOException e) {
      logger.error("HessianSerializer deserialize error:{}", e);
    } finally {
      try {
        is.close();
        hi.close();
      } catch (Exception ex) {
        logger.error("HessianSerializer deserialize close error:{}", ex);
      }
    }

    return null;
  }
}
