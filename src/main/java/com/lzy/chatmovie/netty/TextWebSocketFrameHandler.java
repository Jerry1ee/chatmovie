package com.lzy.chatmovie.netty;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;


/**
 * author:Jerry1ee
 * time:2020.2.1
 *
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ConcurrentHashMap<Channel,String> channelAndUser = new ConcurrentHashMap<>();


    //有客户端连接时，自动调用，将链接的客户端记录下来
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel income = ctx.channel();
        //进入聊天室
//        for (Channel channel : users) {
//                channel.writeAndFlush(new TextWebSocketFrame("欢迎 " + income.remoteAddress() + "进入聊天室！"));
//        }
        users.add(ctx.channel());
        System.out.println(income.remoteAddress()+"加入了！");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outcome = ctx.channel();

        //离开
        for (Channel channel : users) {
                channel.writeAndFlush(new TextWebSocketFrame(channelAndUser.get(outcome) + " 离开了！"));
        }
        users.remove(ctx.channel());
        channelAndUser.remove(outcome);
        System.out.println(channelAndUser.get(outcome)+"离开了！");
    }

    //客户端发送消息时，自动执行
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel income = ctx.channel();//获得发送消息的人的链接通道
//

        // 1.获取客户端发来的消息
        String content = msg.text();
        if(channelAndUser.get(income)==null)
        {
            channelAndUser.put(income,content);
            for (Channel channel : users) {
                channel.writeAndFlush(new TextWebSocketFrame("欢迎 " + content + "进入聊天室！"));
            }
        }else {
            System.out.println(income.toString());
            System.out.println(content);
            System.out.println("此时所有的连接：");
            for (Channel channel : users) {
                System.out.println(channel);
                if (channel != income) {
                    channel.writeAndFlush(new TextWebSocketFrame("[用户]" + channelAndUser.get(income) + "说：" + msg.text()));
                } else {
                    channel.writeAndFlush(new TextWebSocketFrame("我说：" + msg.text()));
                }
            }
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接，随后从channelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }





}
