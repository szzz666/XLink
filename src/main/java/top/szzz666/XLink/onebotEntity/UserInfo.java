package top.szzz666.XLink.onebotEntity;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("status")
    private String status;

    @SerializedName("retcode")
    private int retcode;

    @SerializedName("data")
    private Sender data;

    public UserInfo() {
    }

    public UserInfo(String status, int retcode, Sender data) {
        this.status = status;
        this.retcode = retcode;
        this.data = data;
    }

    /**
     * 获取
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取
     *
     * @return retcode
     */
    public int getRetcode() {
        return retcode;
    }

    /**
     * 设置
     *
     * @param retcode
     */
    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    /**
     * 获取
     *
     * @return data
     */
    public Sender getData() {
        return data;
    }

    /**
     * 设置
     *
     * @param data
     */
    public void setData(Sender data) {
        this.data = data;
    }

    public String toString() {
        return "UserInfo{status = " + status + ", retcode = " + retcode + ", data = " + data + "}";
    }
}
