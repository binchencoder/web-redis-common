package com.jingoal.web.common.queue;

import java.io.Serializable;

/**
 * @description: 通用的消息监听接口
 *
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月15日 下午2:13:18
 */
public interface MessageListener<T extends Serializable> {

    public void onMessage(T message);
    
}
