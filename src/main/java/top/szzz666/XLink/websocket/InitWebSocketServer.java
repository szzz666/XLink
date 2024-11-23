package top.szzz666.XLink.websocket;

import org.java_websocket.server.WebSocketServer;

import static top.szzz666.XLink.XLinkMain.*;


public class InitWebSocketServer {
    public static WebSocketServer server;

    public static void initWebSocketServer() {
        int port = WsServerPort; // 设置 WebSocket 服务器端口
        try {
            server = new MyWebSocketServer(port);
            server.start(); // 启动 WebSocket 服务器
            plugin.getLogger().info("WebSocket服务器在 " + server.getPort() + " 端口上启动");
        } catch (Exception e) {
            plugin.getLogger().error("WebSocket服务器启动失败,查看端口是否被占用");
            myserver.shutdown();
            e.fillInStackTrace();
        }
        plugin.getLogger().info("XLink插件 如有bug请加Q群反馈：894279534");
    }

}
