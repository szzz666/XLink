package top.szzz666.XLink.event;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.scheduler.AsyncTask;
import com.google.gson.Gson;
import top.szzz666.XLink.entity.Message;

import static top.szzz666.XLink.XLinkMain.*;
import static top.szzz666.XLink.utils.onebotUtils.sendGroupAtMsg;
import static top.szzz666.XLink.websocket.InitWebSocketClient.client;
import static top.szzz666.XLink.websocket.MyWebSocketServer.sendMessageToAllWs;


public class PlayerEvents implements Listener {

    public static void sendToQQGroup(String ChatMessageText) {
        for (String QQGroup : QQGroups) {
            sendGroupAtMsg(QQGroup, null, ChatMessageText.replaceAll("§.", ""), null);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        // 取消默认的进服消息提示
        if (HideDefaultJoinMessage) {
            event.setJoinMessage("");
        }
        myserver.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                if (UseServerJoinMessaging) {

                    String JoinMessageText = PlayerJoinMessage.replace("%playerName%", playerName);
                    // 发送自定义的消息给玩家
                    Message message = new Message("Server", JoinMessageText, null);
                    // 创建Gson对象
                    Gson gson = new Gson();
                    // 将对象转换为JSON字符串
                    String jsonMessage = gson.toJson(message);
                    if (UseQQGroupChat)
                        sendToQQGroup(JoinMessageText);
                    myserver.broadcastMessage(JoinMessageText);
                    if (IsParentServer) {
                        sendMessageToAllWs(jsonMessage, null);
                    } else {
                        client.send(jsonMessage);
                    }
                }
            }
        });

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        // 取消默认的进服消息提示
        if (HideDefaultQuitMessage) {
            event.setQuitMessage("");
        }
        myserver.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                if (UseServerQuitMessaging) {
                    String QuitMessageText = PlayerQuitMessage.replace("%playerName%", playerName);
                    // 发送自定义的消息给玩家
                    Message message = new Message("Server", QuitMessageText, null);
                    // 创建Gson对象
                    Gson gson = new Gson();
                    // 将对象转换为JSON字符串
                    String jsonMessage = gson.toJson(message);
                    if (UseQQGroupChat)
                        sendToQQGroup(QuitMessageText);
                    // 发送自定义的消息给玩家
                    myserver.broadcastMessage(QuitMessageText);
                    if (IsParentServer) {
                        sendMessageToAllWs(jsonMessage, null);
                    } else {
                        client.send(jsonMessage);
                    }
                }
            }
        });


    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) { // 玩家聊天事件处理方法
        // 先让消息发送出去
        event.setCancelled(false);
        myserver.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                String playerName = event.getPlayer().getName(); // 获取发送消息的玩家名字
                String playmessage = event.getMessage();// 获取玩家发送的消息内容
                String ChatMessageText = PlayerChatMessage.replace("%playerName%", playerName).replace("%message%", playmessage);
                // 发送自定义的消息给玩家
                Message message = new Message("Server", ChatMessageText, null);
                // 创建Gson对象
                Gson gson = new Gson();
                // 将对象转换为JSON字符串
                String jsonMessage = gson.toJson(message);
                if (UseQQGroupChat)
                    sendToQQGroup(ChatMessageText);
                if (IsParentServer) {
                    sendMessageToAllWs(jsonMessage, null);
                } else {
                    client.send(jsonMessage);
                }
            }
        });
    }
}
