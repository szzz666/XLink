package top.szzz666.XLink.websocket;

import java.net.ConnectException;
import java.net.URI;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.szzz666.XLink.entity.Message;

import static top.szzz666.XLink.XLinkMain.*;
import static top.szzz666.XLink.utils.AES.encrypt;
import static top.szzz666.XLink.websocket.InitWebSocketClient.*;


/**
 * 自定义 WebSocket 客户端类，继承自 WebSocketClient。
 */
public class MyWebSocketClient extends WebSocketClient {


    /**
     * 构造函数，用于初始化 WebSocket 客户端，传入服务器的 URI。
     *
     * @param uri WebSocket 服务器的 URI。
     */
    public MyWebSocketClient(URI uri) {
        super(uri);
    }

    /**
     * 当与服务器建立连接时调用此方法。
     *
     * @param handshakedata 握手数据。
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if (!Objects.equals(WsPassword, "")) {
            // 发送自定义的消息给玩家
            Message message = new Message("Auth", null, encrypt(WsPassword, key));
            // 创建Gson对象
            Gson gson = new Gson();
            // 将对象转换为JSON字符串
            String jsonMessage = gson.toJson(message);
            send(jsonMessage);
        }
        myserver.broadcastMessage("Ws客户端已连接到服务器: " + getURI());
    }

    /**
     * 接收到服务器消息时调用此方法。
     *
     * @param messages_received 服务器发送的消息。
     */
    @Override
    public void onMessage(String messages_received) {
        plugin.getLogger().info("Ws客户端从服务器收到消息: " + messages_received);
        Gson gson = new Gson();
        try {
            Message message = gson.fromJson(messages_received, Message.class);
            myserver.broadcastMessage(message.getText());
        } catch (JsonSyntaxException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 当与服务器断开连接时调用此方法。
     *
     * @param code   断开连接的代码。
     * @param reason 断开连接的原因。
     * @param remote 是否远程关闭连接。
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        myserver.broadcastMessage("Ws客户端已断开与服务端的连接");
        try {
            Thread.sleep(WsClientReconnectTime);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        initWebSocketClient();
    }

    /**
     * 当发生错误时调用此方法。
     *
     */
//    @Override
//    public void onError(Exception ex) {
//        plugin.getLogger().info("Ws客户端发生错误: " + ex.getMessage());
//    }
    public void onError(Exception e) {
        if (!(e instanceof ConnectException)) {
            e.printStackTrace();
        }

    }

}
