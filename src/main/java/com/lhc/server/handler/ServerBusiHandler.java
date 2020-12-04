package com.lhc.server.handler;

import com.lhc.dto.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程和VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：业务处理类
 */
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) {
        System.out.println("业务处理，接收到的消息是：" + myMessage);
    }
}
