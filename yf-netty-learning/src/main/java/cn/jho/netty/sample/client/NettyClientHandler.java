package cn.jho.netty.sample.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端自定义处理类
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-23 7:48
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 通道读取事件-读取服务端发送的消息
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端接收到的消息：" + s);
    }

    /**
     * 通道连接就绪事件-与服务端建立连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("Hello!我是Netty客户端");
    }
}
