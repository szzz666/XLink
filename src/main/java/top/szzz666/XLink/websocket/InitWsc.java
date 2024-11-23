package top.szzz666.XLink.websocket;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;

import static top.szzz666.XLink.XLinkMain.ws_url;
import static top.szzz666.XLink.websocket.InitWebSocketClient.client;

public class InitWsc {
    public static WebSocketClient wsc;

    public static void initWsc() {
        wsc = new Wsc(URI.create(ws_url));
        if (!wsc.isOpen()) {
            wsc.close();
        }
        wsc.connect();
    }
}
