package cn.jho.netty.sample.client;

import cn.jho.netty.sample.properties.ConnectConst;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty客户端
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-23 7:38
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 创建线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        // 创建客户端启动助手
        Bootstrap bootstrap = new Bootstrap();

        // 设置线程组
        bootstrap.group(group)
                // 设置客户端通道实现为NIO
                .channel(NioSocketChannel.class)
                // 创建一个通道初始化对象
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 向pipeline中添加自动逸业务处理handler
                        socketChannel.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new NettyClientHandler());
                    }
                });

        // 启动客户端，等待连接服务端，同时将异步改为同步
        ChannelFuture channelFuture = bootstrap.connect("localhost", ConnectConst.PORT).sync();

        // 关闭通道和连接池
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

}
