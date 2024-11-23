package top.szzz666.XLink.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.ConnectException;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static top.szzz666.XLink.XLinkMain.QQ_wsc;
import static top.szzz666.XLink.XLinkMain.WsClientReconnectTime;
import static top.szzz666.XLink.service.QqMsgServeice.MessageReceived;
import static top.szzz666.XLink.utils.pluginUtil.nkConsole;
import static top.szzz666.XLink.websocket.InitWsc.initWsc;
import static top.szzz666.XLink.websocket.InitWsc.wsc;

/**
 * WebSocket客户端类，用于与服务器建立WebSocket连接并进行通信。
 * 该类继承自WebSocketClient，实现了WebSocket的生命周期回调方法。
 */
public class Wsc extends WebSocketClient {
    private final ScheduledExecutorService scheduler;

    /**
     * 构造函数，初始化WebSocket客户端。
     *
     * @param serverURI 服务器的URI，指定WebSocket的连接地址。
     */
    public Wsc(URI serverURI) {
        super(serverURI);
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    /**
     * 当WebSocket连接成功建立时调用。
     * 此方法中未实现具体逻辑，可根据需求添加连接成功后的处理代码。
     *
     * @param serverHandshake 握手协议的相关信息。
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        nkConsole("连接QQbot成功");
    }

    /**
     * 当从服务器接收到消息时调用。
     * 此方法打印接收到的消息，实际使用时应根据需求解析并处理消息。
     *
     * @param onebotMessage 从服务器接收到的字符串消息。
     */
    @Override
    public void onMessage(String onebotMessage) {
        MessageReceived(onebotMessage);
    }

    /**
     * 当WebSocket连接关闭时调用。
     * 此方法中未实现具体逻辑，可根据需求添加连接关闭时的处理代码。
     *
     * @param i 关闭连接的代码。
     * @param s 关闭连接的原因。
     * @param b 是否是正常关闭。
     */
    @Override
    public void onClose(int i, String s, boolean b) {
        nkConsole("QQ连接已关闭，正在尝试重新连接...", 2);
        // 安排重新连接的任务，例如5秒后尝试
//        scheduler.schedule(this::reconnect, WsClientReconnectTime, TimeUnit.SECONDS);
        try {
            Thread.sleep(WsClientReconnectTime);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        initWsc();
    }

    /**
     * 当发生错误时调用。
     * 此方法中未实现具体逻辑，可根据需求添加错误处理代码。
     *
     * @param e 发生的异常。
     */
    public void onError(Exception e) {
        if (!(e instanceof ConnectException)) {
            e.printStackTrace();
        }
    }
}
