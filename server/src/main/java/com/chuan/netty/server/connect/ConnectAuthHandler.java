package com.chuan.netty.server.connect;

import com.chuan.netty.common.core.model.Request;
import com.chuan.netty.common.core.model.Response;
import com.chuan.netty.common.core.serial.BufferFactory;
import constans.NettyMessageType;
import constans.ResultCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ConnectAuthHandler
 * @包名: com.chuan.netty.server.connect
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/23 11:39
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ConnectAuthHandler extends SimpleChannelInboundHandler<Request>{
    private static Map<String,Boolean> connectedAddress=new ConcurrentHashMap<>();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        //连接认证
        if(request!=null&&request.getType()== NettyMessageType.LOGIN_REQ){
            String address = ctx.channel().remoteAddress().toString();
            //不让同一台主机多次连接
            if(connectedAddress.containsKey(address)){
                //TODO 连接失败
                Response response = buildeConnectResponse(request, ResultCode.ERROR);
                ctx.writeAndFlush(response);
            }else{
                connectedAddress.put(address,true);
                //TODO 连接成功
                Response response = buildeConnectResponse(request, ResultCode.SUCCESS);
                ctx.writeAndFlush(response);
            }
        }else{
            ctx.fireChannelRead(request);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
        ctx.close();
    }

    public Response buildeConnectResponse(Request request,short status){
        ByteBuf buffer = BufferFactory.getBuffer();
        buffer.writeShort(status);
        Response response = new Response(request);
        response.setType(NettyMessageType.LOGIN_RESP);
        response.setData(buffer.array());
        return response;
    }

}
