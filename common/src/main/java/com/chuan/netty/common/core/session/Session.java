package com.chuan.netty.common.core.session;

public interface Session {
    /**
     * 会话绑定对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定对象
     * @return
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     * @return
     */
    void removeAttachment();

    /**
     * 发送消息
     */
    void sendMessage(Object message);

    /**
     * 是否在线
     * @return
     */
    boolean isConnected();

    /**
     * 关闭会话
     */
    void close();

}
