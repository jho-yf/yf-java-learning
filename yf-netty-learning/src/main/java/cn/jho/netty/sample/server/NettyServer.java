package cn.jho.netty.sample.server;

import cn.jho.netty.sample.properties.ConnectConst;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty服务端
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-23 7:15
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建bossGroup线程组：处理网络连接事件
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // 创建workerGroup线程组：处理网络读写事件
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 创建服务端启动助手
        ServerBootstrap bootstrap = new ServerBootstrap();

        // 设置bossGroup线程组和workerGroup线程组
        bootstrap.group(bossGroup, workerGroup)
                // 设置服务器通道实现为NIO
                .channel(NioServerSocketChannel.class)
                // 创建通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 向pipeline中添加自动逸业务处理handler
                        socketChannel.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new NettyServerHandler());
                    }
                });

        // 启动服务并绑定端口，同时将异步改为同步
        ChannelFuture channelFuture = bootstrap.bind(ConnectConst.PORT).sync();
        System.out.println("服务端启动成功...");

        // 关闭通道和连接池
        channelFuture.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}
