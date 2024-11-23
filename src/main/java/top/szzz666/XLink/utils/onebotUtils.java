package top.szzz666.XLink.utils;

import top.szzz666.XLink.onebotEntity.*;

import java.util.ArrayList;

import static top.szzz666.XLink.XLinkMain.QQGroups;
import static top.szzz666.XLink.websocket.InitWsc.wsc;

public class onebotUtils {

    //发送私聊文本消息
    public static void sendPrivateQQMsg(String userId, String text) {
        ArrayList<MessageContent> message = new ArrayList<>();
        message.add(
                new MessageContent(
                        new MessageData(text, null, null), "text"
                )
        );
        SendMsg sendMsg = new SendMsg(
                "send_private_msg",
                new Params(null, userId, message
                )
        );
        String jsonStr = sendMsg.toString();
        wsc.send(jsonStr);
        System.out.println(jsonStr);
    }

    //发送群文本消息
    public static void sendGroupAtMsg(String groupId, String qq, String text, String id) {
        ArrayList<MessageContent> message = new ArrayList<>();
        message.add(
                new MessageContent(
                        new MessageData(null, null, id), "reply"
                )
        );
        message.add(
                new MessageContent(
                        new MessageData(null, qq, null), "at"
                )
        );
        message.add(
                new MessageContent(
                        new MessageData(" " + text, null, null), "text"
                )
        );
        SendMsg sendMsg = new SendMsg(
                "send_group_msg",
                new Params(groupId, null, message
                )
        );
        String jsonStr = sendMsg.toString();
        wsc.send(jsonStr);
    }

    //机器人被是否@
    public static boolean isAted(onebotMsg QQToMsg) {
        if (QQToMsg != null && QQToMsg.getPostType().equals("message")) {
            // 遍历QQToMsg.getMessage()中的所有元素
            for (MessageContent part : QQToMsg.getMessage()) {
                // 确保data和qq字段不为null且消息中的QQ等于自身ID时返回true
                if (part.getData() != null && part.getData().getQq() != null
                        && part.getData().getQq().equals(QQToMsg.getSelfId())) {
                    return true;
                }
            }

        }
        // 如果遍历完没有满足条件的，返回false
        return false;
    }

    //是否是私聊机器人
    public static boolean isPrivate(onebotMsg QQToMsg) {
        if (QQToMsg != null && QQToMsg.getPostType().equals("message")) {
            return QQToMsg.getMessageType().equals("private");
        }
        return false;
    }

    //获得消息文本
    public static String getMsgText(onebotMsg QQToMsg) {
        if (QQToMsg != null && QQToMsg.getPostType().equals("message")) {
            ArrayList<MessageContent> message = QQToMsg.getMessage();
            String msg = " ";
            for (MessageContent messageContent : message) {
                if (messageContent.getType().equals("text")) {
                    msg = messageContent.getData().getText();
                }
            }
            return msg;
        }
        return " ";
    }

    //消息是否来自指定QQ群
    public static boolean isFromGroup(onebotMsg QQToMsg) {
        if (QQToMsg != null && QQToMsg.getPostType().equals("message")) {
            return QQGroups.contains(QQToMsg.getGroupId());
        }
        return false;
    }

    //获取QQ昵称
    public static String getQQNick(String qqUserId) {
        SendMsg sendMsg = new SendMsg(
                "get_stranger_info",
                new Params(null, qqUserId, null
                )
        );
        String jsonStr = sendMsg.toString();
        wsc.send(jsonStr);
        return null;
    }


}
