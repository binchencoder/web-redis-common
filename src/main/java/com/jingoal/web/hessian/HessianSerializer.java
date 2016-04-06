package com.jingoal.web.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.jingoal.web.common.Serializer;

/**
 * @description: Hessian 序列化实现
 * 
 * @company: 北京今目标信息技术有限公司
 * @author: chenbin
 * @time: 2016年4月6日 下午7:50:39
 */
public class HessianSerializer implements Serializer {

    public byte[] serialize(Object object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        try {
            ho.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input hi = new Hessian2Input(is);
        try {
            return hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
