package com.chuan.netty.server.connect;

import com.chuan.netty.common.core.corder.RequestDecorder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NettyServer {
    private static final Logger log= LoggerFactory.getLogger(NettyServer.class);
    private NettyServer(){

    }
    private static NettyServer server=null;
    public void bind(int port){
        ServerBootstrap bootstrap=new ServerBootstrap();
        //创建boss和work线程池
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();
        try{
            bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    //设置缓存
                    .option(ChannelOption.SO_BACKLOG, 2048)
                    //设置管道
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new IdleStateHandler(10,10,20));
                            //TODO 设置心跳检测
                            //TODO 设置编码解码器
                            channel.pipeline().addLast(new RequestDecorder());
                            //TODO 设置事件处理
                            channel.pipeline().addLast();
                        }
                    });
            bootstrap.bind(port).sync();
            log.info("Netty服务启动成功");
        }catch (Exception e){
            //TODO 服务启动失败处理
            log.error(e.toString());
        }finally {
            //关闭资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    /**
     * 单例模式
     * @return
     */
    public static NettyServer getInstance(){
        if(server==null){
            synchronized (server){
                if(server==null){
                    server=new NettyServer();
                    return server;
                }else{
                    return server;
                }
            }
        }else{
            return server;
        }
    }
}
