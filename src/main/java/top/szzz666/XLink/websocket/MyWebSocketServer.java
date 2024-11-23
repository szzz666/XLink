package top.szzz666.XLink.websocket;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import top.szzz666.XLink.entity.Message;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static top.szzz666.XLink.XLinkMain.*;
import static top.szzz666.XLink.utils.AES.decrypt;
import static top.szzz666.XLink.webchat.WebChatAuth.webChatAuth;


/**
 * WebSocket服务器类，用于处理WebSocket连接和消息。
 */
public class MyWebSocketServer extends WebSocketServer {


    static ArrayList<WebSocket> allConn = new ArrayList<>();
    static HashMap<WebSocket, Boolean> AuthSuccessConn = new HashMap<>();

    /**
     * 构造方法，创建一个WebSocket服务器实例，并绑定到指定端口。
     *
     * @param port 服务器监听的端口号
     */
    public MyWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    // 发送消息给所有连接的客户端
    public static void sendMessageToAllWs(String message, WebSocket doNotSend) {
        for (WebSocket conn : allConn) {
            if (conn != doNotSend && conn.isOpen()) {
                conn.send(message);
            }
        }
    }


    /**
     * 当有WebSocket连接关闭时调用此方法。
     *
     * @param conn   关闭的WebSocket连接
     * @param code   关闭代码
     * @param reason 关闭原因
     * @param remote 是否是远程关闭
     */
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        myserver.broadcastMessage("Ws服务端已经断开了：" + conn.getRemoteSocketAddress());
        allConn.remove(conn);
    }

    /**
     * 当WebSocket连接发生错误时调用此方法。
     *
     * @param conn 发生错误的WebSocket连接
     * @param ex   异常信息
     */
    @Override
    public void onError(WebSocket conn, Exception ex) {
        try {
            plugin.getLogger().error("Ws服务端连接时发生了错误：" + conn.getRemoteSocketAddress() + ": " + ex.getMessage());
        } catch (Exception e) {
            plugin.getLogger().error("WebSocket服务器启动失败,查看端口是否被占用");
            myserver.shutdown();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStart() {

    }

    /**
     * 当接收到WebSocket客户端发送的消息时调用此方法。
     *
     * @param conn              发送消息的WebSocket连接
     * @param messages_received 接收到的消息内容
     */
    @Override
    public void onMessage(WebSocket conn, String messages_received) {
        // 在这里处理接收到的消息
        myserver.getLogger().info("Ws服务端收到消息：" + conn.getRemoteSocketAddress().toString() + ": " + messages_received);
        Gson gson = new Gson();
        Message message = gson.fromJson(messages_received, Message.class);
        if (!WsPassword.equals("null") && !WsPassword.isEmpty()) {
            InetSocketAddress ip = conn.getRemoteSocketAddress();
            //认证
            if ("Auth".equals(message.getType())) {
                if (WsPassword.equals(decrypt(message.getPassword(), key))) {
                    AuthSuccessConn.put(conn, true);
                    allConn.add(conn);
                    myserver.getLogger().info("认证成功：" + ip);
                } else if (webChatAuth(message.getPassword())) {
                    AuthSuccessConn.put(conn, true);
                    allConn.add(conn);
                    conn.send("200");
                    myserver.getLogger().info("认证成功：" + ip);
                } else {
                    conn.send("500");
                }
                //断开没有认证的连接
                if (AuthSuccessConn.get(conn) == null) {
                    conn.close();
                }
            }

        }
        //聊天转发
        if ("Server".equals(message.getType())) {
            sendMessageToAllWs(messages_received, conn);
            myserver.broadcastMessage(message.getText());
        }

    }

    /**
     * 当有新的WebSocket连接建立时调用此方法。
     *
     * @param conn      新建立的WebSocket连接
     * @param handshake 握手信息
     */
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        myserver.getLogger().info("Ws服务端已经连接上：" + conn.getRemoteSocketAddress());
        if (WsPassword != null && !WsPassword.equals("null") && !WsPassword.equals("")) {
            //延迟执行任务
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    //断开没有认证的连接
                    if (AuthSuccessConn.get(conn) == null) {
                        conn.close();
                        plugin.getLogger().info("认证超时：" + conn.getRemoteSocketAddress());
                    }
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000); // 延迟5秒后执行
        } else {
            allConn.add(conn);
        }
    }
}

