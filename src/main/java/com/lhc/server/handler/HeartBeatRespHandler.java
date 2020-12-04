package com.lhc.server.handler;


import com.lhc.constant.MessageTypeEnum;
import com.lhc.dto.MyHeader;
import com.lhc.dto.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程和VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：心跳应答
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(HeartBeatRespHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        MyMessage message = (MyMessage) msg;
        // 返回心跳应答消息
        if (message.getMyHeader() != null
                && message.getMyHeader().getType() == MessageTypeEnum.HEARTBEAT_REQ
                .value()) {
            LOG.info("Receive client:" + ctx.channel().remoteAddress().toString() + "  heart beat message : ---> "
                    + message);
            MyMessage heartBeat = buildHeatBeat();
            LOG.info("Send heart beat response message to client:" + ctx.channel().remoteAddress().toString() + ": ---> "
                    + heartBeat);
            ctx.writeAndFlush(heartBeat);
            ReferenceCountUtil.release(msg);
        } else
            ctx.fireChannelRead(msg);
    }

    /*心跳应答报文*/
    private MyMessage buildHeatBeat() {
        MyMessage message = new MyMessage();
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageTypeEnum.HEARTBEAT_RESP.value());
        message.setMyHeader(myHeader);
        return message;
    }

}
