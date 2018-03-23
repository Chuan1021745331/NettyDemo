package com.chuan.netty.common.core.session;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: SessionImpl
 * @包名: com.chuan.netty.common.core.session
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/22 16:02
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class SessionImpl implements Session{
    private Channel channel;

    /**
     * 绑定对象key
     */
    public static AttributeKey<Object> ATTACHMENT_KEY  = AttributeKey.valueOf("ATTACHMENT_KEY");

    public SessionImpl(Channel channel){
        this.channel=channel;
    }

    @Override
    public Object getAttachment() {
        Attribute<Object> attr = channel.attr(ATTACHMENT_KEY);
        return attr.get();
    }

    @Override
    public void setAttachment(Object attachment) {
        channel.attr(ATTACHMENT_KEY).set(attachment);
    }

    @Override
    public void removeAttachment() {
        channel.attr(ATTACHMENT_KEY).remove();
    }

    @Override
    public void sendMessage(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public void close() {
        channel.close();
    }
}
