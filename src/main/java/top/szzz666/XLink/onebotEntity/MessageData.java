package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MessageData {
    @SerializedName("text")
    private String text;
    @SerializedName("qq")
    private String qq;
    @SerializedName("id")
    private String id;

    public MessageData() {
    }


    public MessageData(String text, String qq, String id) {
        this.text = text;
        this.qq = qq;
        this.id = id;
    }


    /**
     * 获取
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * 设置
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 获取
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
