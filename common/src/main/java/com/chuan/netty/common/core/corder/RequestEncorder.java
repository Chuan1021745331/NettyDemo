package com.chuan.netty.common.core.corder;

import com.chuan.netty.common.core.model.Request;
import constans.NettyMessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   类型   |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * @author -琴兽-
 *
 */
public class RequestEncorder extends MessageToByteEncoder<Request>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(NettyMessageType.HEADER_FLAG);
        byteBuf.writeShort(request.getModule());
        byteBuf.writeShort(request.getCmd());

    }
}
