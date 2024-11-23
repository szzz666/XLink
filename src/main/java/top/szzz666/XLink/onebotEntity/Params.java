package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Params {
    @SerializedName("group_id")
    private String group_id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("message")
    private List<MessageContent> message;

    public Params() {
    }
    public Params(String group_id, String user_id, List<MessageContent> message) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.message = message;
    }


    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public List<MessageContent> getMessage() {
        return message;
    }

    public void setMessage(List<MessageContent> message) {
        this.message = message;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 获取
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
