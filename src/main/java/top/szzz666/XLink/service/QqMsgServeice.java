package top.szzz666.XLink.service;

import cn.nukkit.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import top.szzz666.XLink.onebotEntity.onebotMsg;

import static top.szzz666.XLink.XLinkMain.*;
import static top.szzz666.XLink.event.PlayerEvents.sendToQQGroup;
import static top.szzz666.XLink.utils.onebotUtils.getMsgText;
import static top.szzz666.XLink.utils.onebotUtils.isFromGroup;


public class QqMsgServeice {

    public static void MessageReceived(String QQToJsonMsg) {
        onebotMsg QQToMsg = null;
//        UserInfo userInfo = null;
        try {
            QQToMsg = new Gson().fromJson(QQToJsonMsg, onebotMsg.class);
        } catch (JsonSyntaxException e) {
            e.fillInStackTrace();
        }
        //qq消息处理
        if (isFromGroup(QQToMsg)) {
            //命令消息
            String textMsg = getMsgText(QQToMsg);
            if (QQQueryCmd.contains(textMsg)) {
                //遍历服务器所有玩家
                StringBuilder players = new StringBuilder();
                int playerNum = 0;
                players.append("\n");
                for (Player serverPlayer : myserver.getOnlinePlayers().values()) {
                    playerNum++;
                    players.append(serverPlayer.getName()).append("\n");
                }
                String playerList = players.toString();
                if (!QueryServerMessage.isEmpty()) {
                    sendToQQGroup(QueryServerMessage.replaceAll("%players%", "\n" + playerList)
                            .replaceAll("%playerNum%", String.valueOf(playerNum)
                            ));
                }
            }


            //消息广播
            String msg = QQToMsg.getRawMessage();
            // 替换 [CQ:xxx,xxx] 为 [xx]
            msg = msg.replaceAll("\\[CQ:image,[^\\]]*\\]", "[图片]");
            msg = msg.replaceAll("\\[CQ:face,[^\\]]*\\]", "[表情]");
            msg = msg.replaceAll("\\[CQ:video,[^\\]]*\\]", "[视频]");
            msg = msg.replaceAll("\\[CQ:json,[^\\]]*\\]", "[小程序]");
            msg = msg.replaceAll("\\[CQ:record,[^\\]]*\\]", "[语音]");
            msg = msg.replaceAll("\\[CQ:reply,[^\\]]*\\]", "[回复]");
            // 替换 [CQ:at,qq=xxx] 为 [@xxx]
            msg = msg.replaceAll("\\[CQ:at,qq=all]*\\]", "[§b@§f全体成员]");
            msg = msg.replaceAll("\\[CQ:at,qq=([0-9]+)\\]", "[§b@§f$1]");
            String name = QQToMsg.getSender().getNickname();
            String textMessage = QQChatMessage.replaceAll("%name%", name).replaceAll("%message%", msg);
            myserver.broadcastMessage(textMessage);

        }

    }
}
