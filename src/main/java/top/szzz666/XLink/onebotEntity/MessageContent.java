package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MessageContent {
    @SerializedName("data")
    private MessageData data;

    @SerializedName("type")
    private String type;


    public MessageContent() {
    }

    public MessageContent(MessageData data, String type) {
        this.data = data;
        this.type = type;
    }

    /**
     * 获取
     * @return data
     */
    public MessageData getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(MessageData data) {
        this.data = data;
    }

    /**
     * 获取
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
