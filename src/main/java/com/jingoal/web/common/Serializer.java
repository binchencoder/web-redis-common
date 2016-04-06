package com.jingoal.web.common;

/**
 * @description: 序列化接口
 *
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月6日 下午7:48:15
 */
public interface Serializer {

    /**
     * @description: 序列化接口
     *
     * @param 序列化对象
     * @return 字节流
     * 
     * @author: chenbin
     * @since: since from which version support
     * @time: 2016年4月6日 下午7:48:31
     */
    byte[] serialize(Object obj);

    /**
     * @description: 反序列化接口
     *
     * <p>detailed description</p> 
     * 
     * @param bytes 字节流
     * @return 对象
     * 
     * @author: chenbin
     * @since: since from which version support
     * @time: 2016年4月6日 下午7:48:49
     */
    Object deserialize(byte[] bytes);
}
