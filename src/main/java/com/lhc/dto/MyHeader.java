package com.lhc.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MyHeader {
    private int crcCode = 0xabef0101;
    private int length;// 消息长度
    private long sessionID;// 会话ID
    private byte type;// 消息类型
    private byte priority;// 消息优先级
    private Map<String, Object> attachment = new HashMap<String, Object>(); // 附件
}