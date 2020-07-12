package com.lagou.client;

import com.lagou.serializer.impl.JSONSerializer;
import com.lagou.vo.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.IOException;
import java.util.concurrent.Callable;

public class UserClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;
    private RpcRequest para;


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        context = ctx;
    }

    /**
     * 收到服务端数据，唤醒等待线程
     */

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg.toString();
        notify();
    }

    /**
     * 写出数据，开始等待唤醒
     */

    public synchronized Object call() throws InterruptedException {
        try {
            context.writeAndFlush(para);
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     设置参数
     */
    void setPara(RpcRequest para) {
        this.para = para;
    }
}
