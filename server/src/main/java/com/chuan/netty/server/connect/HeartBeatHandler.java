package com.chuan.netty.server.connect;

import com.chuan.netty.common.core.model.Request;
import com.chuan.netty.common.core.model.Response;
import com.sun.corba.se.impl.presentation.rmi.IDLTypeException;
import constans.NettyMessageType;
import constans.NettyServerConstants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: HeartBeatHandler
 * @包名: com.chuan.netty.server.connect
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/23 10:59
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class HeartBeatHandler extends SimpleChannelInboundHandler<Request>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        if(request!=null&&request.getType()== NettyMessageType.HEART_REQ){
            Response response = buidHeartResponse(NettyServerConstants.HEART_RESP_MESSAGE);
            channelHandlerContext.channel().writeAndFlush(response);
        }else{
            channelHandlerContext.fireChannelRead(request);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if(evt instanceof IdleStateEvent)
        {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch(e.state())
            {
                case READER_IDLE:
                    //TODO 读超时
                    break;
                case WRITER_IDLE:
                    //TODO 写超时
                    break;
                case ALL_IDLE:
                    //TODO 读写超时,关闭当前连接，释放缓存资源
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    public Response buidHeartResponse(String message){
        Response response = new Response();
        response.setModule((short)-1);
        response.setCmd((short)-1);
        response.setType(NettyMessageType.HEART_RESP);
        if(message!=null){
            response.setData(message.getBytes());
        }else{
            response.setData(null);
        }
        return response;
    }
}
