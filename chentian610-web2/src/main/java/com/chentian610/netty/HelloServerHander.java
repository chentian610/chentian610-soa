package com.chentian610.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Created by tuto on 2016-12-19 .
 */
public class HelloServerHander extends SimpleChannelHandler {

    /**
     * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(
            ChannelHandlerContext ctx,
            ChannelStateEvent e) {
        System.out.println("Hello world, I'm server.");
    }
}
