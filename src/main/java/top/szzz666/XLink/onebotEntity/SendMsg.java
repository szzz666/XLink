package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SendMsg {
    @SerializedName("action")
    private String action;
    @SerializedName("params")
    private Params params;

    public SendMsg() {
    }

    public SendMsg(String action, Params params) {
        this.action = action;
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }


    public String toString() {
        return new Gson().toJson(this);
    }
}
