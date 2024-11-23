package top.szzz666.XLink.websocket;


import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

import static top.szzz666.XLink.XLinkMain.*;


public class InitWebSocketClient {
    public static WebSocketClient client;

    public static void initWebSocketClient() {
        try {
            String serverURI = WsConnectURL; // 连接的服务器 URI
            client = new MyWebSocketClient(new URI(serverURI)); // 创建 WebSocket 客户端实例
            plugin.getLogger().info("正在连接父服务器...");
            client.connect();
            if (!client.isOpen()) {
                client.close();
            }
        } catch (URISyntaxException e/* | InterruptedException e */) {
            e.printStackTrace();
        }
    }

}




