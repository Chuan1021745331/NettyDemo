package com.chuan.netty.server.connect;

import com.chuan.netty.common.core.model.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ServerHandler
 * @包名: com.chuan.netty.server.connect
 * @描述: (用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2018/3/23 14:38
 * @版本: V1.0
 * @创建人：JC
 * @修改人：JC
 * @版权: 2018 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ServerHandler extends SimpleChannelInboundHandler<Request> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

    public void handleMessage(Request request){

    }
}
