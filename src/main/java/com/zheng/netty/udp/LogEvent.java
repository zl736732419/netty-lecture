package com.zheng.netty.udp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetSocketAddress;

/**
 * @Author zhenglian
 * @Date 2019/6/17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {
    public transient static final byte SEPERATOR = (byte) ',';
    private InetSocketAddress source;
    private String logfile;
    private String msg;
    private Long receivedTime;
}
