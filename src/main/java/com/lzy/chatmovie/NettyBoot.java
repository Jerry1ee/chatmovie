package com.lzy.chatmovie;

import com.lzy.chatmovie.netty.WebChatServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/**
 * Netty启动类
 * @author lzy
 * @since 2020.2.1
 */

@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(event.getApplicationContext().getParent() == null)
        {
            try {
                WebChatServer.getInstance().start();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
