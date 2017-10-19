package com.chentian610.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Netty 客户端代码
 *
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class HelloClient {

    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloClientHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        bootstrap.connect(new InetSocketAddress(
                "127.0.0.1", 8000));
    }

    private static class HelloClientHandler extends SimpleChannelHandler {


        /**
         * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
         *
         * @alia OneCoder
         * @author lihzh
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx,
                                     ChannelStateEvent e) {
            System.out.println("Hello world, I'm client.");
        }
    }
}