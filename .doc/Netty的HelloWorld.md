# Netty

https://netty.io/index.html

## 1.BIO、NIO、AIO区别

- BIO 同步阻塞	服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。
- NIO 同步非阻塞 服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
- AIO 异步非阻塞 服务器实现模式为一个有效请求一个线程，客户端的I/O请求都是由OS先完成了再通知服务器应用去启动线程进行处理。

## 2.Reactor线程模型

### 2.1单线程模型

所有IO操作都由同一个NIO线程处理

### 2.2多线程模型

由一组NIO线程处理IO操作，使用线程池

### 2.3主从线程模型

一组线程池接收请求，另一组线程池处理IO

## 3.构建一个Netty服务器

https://mvnrepository.com/search?q=netty

#### 首先，构建一个服务器启动类，具体步骤如下：

- 构建一对主从线程组
- 定义服务器启动类
- 为服务器设置channel
- 设置处理从线程池的助手类初始化器
- 监听启动和关闭服务器

*WebChatServer.java:*

```java
public class WebChatServer {
    private int port;
    public WebChatServer(int port)
    {
        this.port=port;
    }

    public void start()
    {
        //定义一对线程组

        //主线程组，用于接收客户端链接，不做任何处理，即只处理客户端接入
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        //从线程组，主线程组将任务丢给从线程组，处理客户端具体请求
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            //netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)  //设置主从线程组
                    .channel(NioServerSocketChannel.class)//确定通道类nio
                   // .option(ChannelOption.SO_BACKLOG,128)
                   // .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new HelloNettyServerInitializer());                //子处理器，用于处理workGroup

            //启动server，设置8088为启动端口号，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("[系统消息]：服务器已经启动！");
            //监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
```

服务器启动时，调用group函数设置主从线程，要注意**childHandler**函数，实际上相当于添加拦截器（助手类），当客户端与服务端建立连接时，Channel被建立起来，此时通过pipeline进行交互，pipeline中有多个handler，handler就是过滤的规则。

#### 用户定义的初始化器，channel注册后，会执行里面的方法

初始化channel时，会调用一些助手类

*HelloNettyServerInitializer.java*

```java
public class WebchatServerInitialize extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();
		//添加handler
        //以下有些是netty官方给的handler，有些是自定义的handler
        pipeline.addLast(new HttpServerCodec())//将请求和应答消息编码或解码为 http消息
                .addLast(new CustomHandler());


    }
}
```

#### 创建自定义助手类



```java
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        //获取channel
        Channel channel = ctx.channel();
        //显示客户端ip地址
        System.out.println(channel.remoteAddress());
        //定义发送的数据消息
        ByteBuf content= Unpooled.copiedBuffer("Hello Netty!", CharsetUtil.UTF_8);
        //构建http response
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                content
        );

        //为响应增加数据类型和长度
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

        ctx.writeAndFlush(response);
    }
}
```

成了，一个简单的 Netty的hello world！

