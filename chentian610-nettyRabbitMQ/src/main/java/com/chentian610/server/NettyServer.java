package com.chentian610.server;

import com.chentian610.example.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patterncat on 2016/4/6.
 */
@Component
@RabbitListener(queues = "messageQueue")
//实现ApplicationContextAware以获得ApplicationContext中的所有bean
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private Channel clientChannel;

    private Map<String, Object> exportServiceMap = new HashMap<String, Object>();

    @Value("${rpcServer.host:127.0.0.1}")
    String host;

    @Value("${rpcServer.ioThreadNum:5}")
    int ioThreadNum;
    //内核为此套接口排队的最大连接个数，对于给定的监听套接口，内核要维护两个队列，未链接队列和已连接队列大小总和最大值

    @Value("${rpcServer.backlog:1024}")
    int backlog;

    @Value("${rpcServer.port:9090}")
    int port;

    /**
     * 启动
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        logger.info("begin to start rpc server");
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(ioThreadNum);

        ServerBootstrap serverBootstrap = new ServerBootstrap();// (2)ServerBootstrap 是一个启动 NIO 服务的辅助启动类。
        // 你可以在这个服务中直接使用 Channel，但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)这里我们指定使用 NioServerSocketChannel 类来举例说明一个新的 Channel 如何接收进来的连接。
                .childHandler(new WebsocketChatServerInitializer())  //(4)这里的事件处理类经常会被用来处理一个最近的已经接收的 Channel。
                // SimpleChatServerInitializer 继承自ChannelInitializer 是一个特殊的处理类，他的目的是帮助使用者配置一个新的 Channel。
                // 也许你想通过增加一些处理类比如 SimpleChatServerHandler 来配置一个新的 Channel 或者其对应的ChannelPipeline 来实现你的网络程序。
                // 当你的程序变的复杂时，可能你会增加更多的处理类到 pipline 上，然后提取这些匿名类到最顶层的类上。
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)你可以设置这里指定的 Channel 实现的配置参数。
                // 我们正在写一个TCP/IP 的服务端，因此我们被允许设置 socket 的参数选项比如tcpNoDelay 和 keepAlive。
                // 请参考 ChannelOption 和详细的 ChannelConfig 实现的接口文档以此可以对ChannelOption 的有一个大概的认识。
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)option() 是提供给NioServerSocketChannel 用来接收进来的连接。
        // childOption() 是提供给由父管道 ServerChannel 接收到的连接，在这个例子中也是 NioServerSocketChannel。

        System.out.println("WebsocketChatServer 启动了" + port);

        channel = serverBootstrap.bind(host,port).sync().channel();
        logger.info("NettyRPC server listening on port " + port + " and ready for connections...");
    }

    @PreDestroy
    public void stop() {
        logger.info("destroy server resources");
        if (null == channel) {
            logger.error("server channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }

    @RabbitHandler
    public void process(String message) {
        System.out.println("process message.error  : " + message);
        ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        ChannelGroup channels = HttpRequestHandler.channels;
        channels.writeAndFlush(new TextWebSocketFrame(message));
    }


}
