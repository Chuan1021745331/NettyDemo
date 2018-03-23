package com.chuan.netty.common.core.session;

import com.chuan.netty.common.core.model.Response;
import com.chuan.netty.common.core.serial.Serializer;
import com.google.protobuf.GeneratedMessage;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    /**
     * 在线用户
     */
    private static ConcurrentHashMap<Long,Session> onlineSession=new ConcurrentHashMap<>();

    /**
     * 加入在线用户
     * @param userId
     * @param session
     * @return
     */
    public static boolean putSession(long userId,Session session){
        //判断是否存在,如果已经存在不让加入，必须先移除原先会话再加入
        if(!onlineSession.containsKey(userId)){
            boolean success = onlineSession.putIfAbsent(userId, session)== null? true : false;
            return success;
        }
        return false;
    }

    /**
     * 发送消息[自定义协议]
     * @param <T>
     * @param playerId
     * @param message
     */
    public static <T extends Serializer> void sendMessage(long playerId, short module, short cmd, short type,T message){
        Session session = onlineSession.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, type,message.getBytes());
            session.sendMessage(response);
        }
    }

    /**
     * 发送消息[protoBuf协议]
     * @param <T>
     * @param playerId
     * @param message
     */
    public static <T extends GeneratedMessage> void sendMessage(long playerId, short module, short cmd,short type, T message){
        Session session = onlineSession.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, type,message.toByteArray());
            session.sendMessage(response);
        }
    }


    /**
     * 根据用户id获取用户会话
     * @param userId
     * @return
     */
    public static Session getSession(long userId){
        Session session = onlineSession.get(userId);
        return session;
    }

    /**
     * 移除会话,断开连接
     * @param userId
     * @return
     */
    public static void removeSession(long userId){
        Session session = onlineSession.get(userId);
        if(session!=null){
            session.removeAttachment();
        }
        onlineSession.remove(userId);
    }

    public boolean isOnline(long userId){
        Session session = onlineSession.get(userId);
        if(session!=null){
            return false;
        }
        return session.isConnected();
    }

    /**
     * 获取所有在线用户Id
     * @return
     */
    public static Set<Long> getOnlineUser(){
        return Collections.unmodifiableSet(onlineSession.keySet());
    }
}
