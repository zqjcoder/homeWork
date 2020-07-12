package com.lagou.handler;

import com.lagou.serializer.Serializer;
import com.lagou.serializer.impl.JSONSerializer;
import com.lagou.service.UserService;
import com.lagou.service.UserServiceImpl;
import com.lagou.vo.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class UserServerHandler extends ChannelInboundHandlerAdapter implements ApplicationContextAware {

    @Autowired
    private UserService userService;

    private ApplicationContext context;



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 判断是否符合约定，符合则调用本地方法，返回数据
        // msg:  UseqrService#sayHello#are you ok?
        RpcRequest request = (RpcRequest) msg;
        Class<?> aClass = Class.forName(request.getClassName());
        Method method = aClass.getMethod(request.getMethodName(), request.getParameterTypes());
        Object bean = context.getBean(aClass);
        Object result = method.invoke(bean, request.getParameters());
        ctx.writeAndFlush("success");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
