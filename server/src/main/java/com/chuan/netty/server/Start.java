package com.chuan.netty.server;

import com.chuan.netty.server.connect.NettyServer;
import constans.NettyServerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Start {
    private static final Logger log= LoggerFactory.getLogger(Start.class);
    public static void main(String args[]){
        NettyServer instance = NettyServer.getInstance();
        try{
            instance.bind(NettyServerConstants.NETTYSERVER_PORT);
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
