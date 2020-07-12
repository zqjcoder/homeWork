package com.lagou.server;

import com.lagou.decoder.RpcDecoder;
import com.lagou.encoder.RpcEncoder;
import com.lagou.handler.UserServerHandler;
import com.lagou.serializer.impl.JSONSerializer;
import com.lagou.service.UserService;
import com.lagou.service.UserServiceImpl;
import com.lagou.vo.RpcRequest;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ServerBootstrap implements CommandLineRunner {

    @Autowired
    private UserServerHandler userServerHandler;

    @Override
    public void run(String... args) throws Exception {
        startServer("127.0.0.1", 8888);
    }

    //hostName:ip地址  port:端口号
    private void startServer(String hostName,int port) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        io.netty.bootstrap.ServerBootstrap serverBootstrap = new io.netty.bootstrap.ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
//                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new RpcDecoder(RpcRequest.class, new JSONSerializer()));
                        pipeline.addLast(userServerHandler);

                    }
                });
        serverBootstrap.bind(hostName,port).sync();
    }
}
