package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class onebotMsg {
    @SerializedName("self_id")
    private String selfId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("time")
    private String time;

    @SerializedName("message_id")
    private String messageId;

    @SerializedName("real_id")
    private String realId;

    @SerializedName("message_seq")
    private String messageSeq;

    @SerializedName("message_type")
    private String messageType;

    @SerializedName("sender")
    private Sender sender;

    @SerializedName("raw_message")
    private String rawMessage;

    @SerializedName("font")
    private int font;

    @SerializedName("sub_type")
    private String subType;

    @SerializedName("message")
    private ArrayList<MessageContent> message;

    @SerializedName("message_format")
    private String messageFormat;

    @SerializedName("post_type")
    private String postType;

    @SerializedName("group_id")
    private String groupId;

    public onebotMsg() {
    }

    public onebotMsg(String selfId, String userId, String time, String messageId, String realId, String messageSeq, String messageType, Sender sender, String rawMessage, int font, String subType, ArrayList<MessageContent> message, String messageFormat, String postType, String groupId) {
        this.selfId = selfId;
        this.userId = userId;
        this.time = time;
        this.messageId = messageId;
        this.realId = realId;
        this.messageSeq = messageSeq;
        this.messageType = messageType;
        this.sender = sender;
        this.rawMessage = rawMessage;
        this.font = font;
        this.subType = subType;
        this.message = message;
        this.messageFormat = messageFormat;
        this.postType = postType;
        this.groupId = groupId;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 获取
     * @return selfId
     */
    public String getSelfId() {
        return selfId;
    }

    /**
     * 设置
     * @param selfId
     */
    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    /**
     * 获取
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取
     * @return messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取
     * @return realId
     */
    public String getRealId() {
        return realId;
    }

    /**
     * 设置
     * @param realId
     */
    public void setRealId(String realId) {
        this.realId = realId;
    }

    /**
     * 获取
     * @return messageSeq
     */
    public String getMessageSeq() {
        return messageSeq;
    }

    /**
     * 设置
     * @param messageSeq
     */
    public void setMessageSeq(String messageSeq) {
        this.messageSeq = messageSeq;
    }

    /**
     * 获取
     * @return messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * 设置
     * @param messageType
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取
     * @return sender
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * 设置
     * @param sender
     */
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    /**
     * 获取
     * @return rawMessage
     */
    public String getRawMessage() {
        return rawMessage;
    }

    /**
     * 设置
     * @param rawMessage
     */
    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    /**
     * 获取
     * @return font
     */
    public int getFont() {
        return font;
    }

    /**
     * 设置
     * @param font
     */
    public void setFont(int font) {
        this.font = font;
    }

    /**
     * 获取
     * @return subType
     */
    public String getSubType() {
        return subType;
    }

    /**
     * 设置
     * @param subType
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * 获取
     * @return message
     */
    public ArrayList<MessageContent> getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(ArrayList<MessageContent> message) {
        this.message = message;
    }

    /**
     * 获取
     * @return messageFormat
     */
    public String getMessageFormat() {
        return messageFormat;
    }

    /**
     * 设置
     * @param messageFormat
     */
    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    /**
     * 获取
     * @return postType
     */
    public String getPostType() {
        return postType;
    }

    /**
     * 设置
     * @param postType
     */
    public void setPostType(String postType) {
        this.postType = postType;
    }

    /**
     * 获取
     * @return groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置
     * @param groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
