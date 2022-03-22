package cn.jho.netty.sample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端业务处理类
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-23 7:31
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 通道读取就绪事件-接收客户端请求
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务端接收到的消息：" + s);
    }

    /**
     * 通道读取完毕事件-响应客户端
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("Hello!我是Netty服务端");
    }
}
