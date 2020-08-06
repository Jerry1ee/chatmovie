package com.lzy.chatmovie.netty;


/**
 * 发送消息的动作枚举
 * @author lzy
 * @since 2020.4.6
 */
public enum  MsgActionEnum {

    CONNECT(1,"第一次初始化连接"),
    CHAT(2,"聊天消息"),
    KEEPALIVE(3,"客户端保持心跳"),
    SIGNED(4,"消息签收");

    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type, String content)
    {
        this.type = type;
        this.content = content;
    }

}
