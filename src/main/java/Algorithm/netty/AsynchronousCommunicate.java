package Algorithm.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class AsynchronousCommunicate {
//    当收到来自客户端的数据后，Netty 就会在我们第一行提供的 EventLoopGroup 对象中，获取一个 IO 线程，
//    在这个 IO 线程中调用接收数据的回调方法，来执行接收数据的业务逻辑，
//    在这个例子中，就是我们传入的 MyHandler 中的方法。


//    这个设置 Handler 的过程，就是 预先来定义回调方法的过程。
    static class MyHandler extends ChannelInboundHandlerAdapter {
    }

    private void startNioServer() throws InterruptedException {
        // 创建一组线性
        //这组线程的作用就是来执行收发数据的业务逻辑。
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 初始化Server
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress("localhost", 9999));

            // 设置收到数据后的处理的Handler
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(new MyHandler());
                }
            });
            // 绑定端口，开始提供服务
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {

    }
}
