package com.chuan.netty.server.scanner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: InvokerManager
 * @包名: com.chuan.netty.server.scanner
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/23 15:02
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class InvokerManager {
    public static Map<Short,Map<Short,Invoker>> invokers=new ConcurrentHashMap<>();

    /**
     * 根据模块号与命令号存入invoker
     * @param modul
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(short modul,short cmd,Invoker invoker){
        if(invokers.containsKey(modul)){
            Map<Short, Invoker> invokerMap = invokers.get(modul);
            invokerMap.put(cmd,invoker);
        }else{
            Map<Short, Invoker> invokerMap= new ConcurrentHashMap<>();
            invokerMap.put(cmd,invoker);
            invokers.put(modul,invokerMap);
        }
    }

    /**
     * 根据模块号与命令号拿到invoker
     * @param modul
     * @param cmd
     * @return
     */
    public static Invoker getInvoker(short modul,short cmd){
        Map<Short, Invoker> invokerMap = invokers.get(modul);
        if(invokerMap==null){
            return null;
        }
        return invokerMap.get(cmd);
    }
}
