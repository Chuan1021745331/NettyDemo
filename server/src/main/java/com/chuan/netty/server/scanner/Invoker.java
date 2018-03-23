package com.chuan.netty.server.scanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: Invoker
 * @包名: com.chuan.netty.server.scanner
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/23 14:54
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Invoker {
    /**
     * 方法对象
     */
    private Object target;

    /**
     *方法
     */
    private Method method;

    private Invoker(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public static Invoker valueOf(Object target,Method method){
        return new Invoker(target,method);
    }

    public Object invoke(Object ... params){
        if(target!=null&&method!=null){
            try {
                return method.invoke(target,params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }
}
