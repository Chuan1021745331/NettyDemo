package com.chuan.netty.common.core.corder;

import com.chuan.netty.common.core.model.Request;
import constans.NettyMessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

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
public class RequestDecorder extends ByteToMessageDecoder{
    private static final int BASCE_LENGTH=4+2+2+2+4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while(true){
            //判断长度,比基长度还小，需等待数据到来
            if(byteBuf.readableBytes()<BASCE_LENGTH){
                return;
            }
            int beginReaderIndex = byteBuf.readerIndex();
            while(true){
                //标记当前读指针
                byteBuf.markReaderIndex();
                //读取包头
                if(NettyMessageType.HEADER_FLAG==byteBuf.readInt()){
                    break;
                }else{
                    //没读取到包头返回指针，读指针向前移动一位
                    byteBuf.resetReaderIndex();
                    byteBuf.readBytes(1);
                }
            }
            short module = byteBuf.readShort();
            short cmd = byteBuf.readShort();
            short type = byteBuf.readShort();

            //数据包长度
            int length = byteBuf.readInt();
            if(length<0){
                channelHandlerContext.channel().close();
            }
            //可读数据不够,buffer返回最初读取指针位置
            if(byteBuf.readableBytes()<length){
                byteBuf.readerIndex(beginReaderIndex);
                return;
            }

            byte[] data = new byte[length];
            byteBuf.readBytes(data);

            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setType(type);
            request.setData(data);
            //交给下一个handler处理
            list.add(request);
        }

    }
}
