package com.lzy.chatmovie.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * author:Jerry1ee
 * time:2020.2.1
 *
 */
@Component
public class WebChatServer {

    private static class SingletonWebChatServer{
        static final WebChatServer instance = new WebChatServer();
    }

    public static WebChatServer getInstance()
    {
        return new SingletonWebChatServer().instance;
    }
    private EventLoopGroup bossGroup ;
    private EventLoopGroup workGroup ;
    private ServerBootstrap server;
    private ChannelFuture channelFuture;

    public WebChatServer(){
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebChatServerInitializer())
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
    }
    public void start()
    {
        this.channelFuture = server.bind(8088);
        System.out.println("服务器已经启动...");
    }

}
