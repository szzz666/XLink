package top.szzz666.XLink.entity;

public class Message {
    private String type;
    private String text;
    private String password;
    public Message() {
    }


    public Message(String type, String text, String password) {
        this.type = type;
        this.text = text;
        this.password = password;
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
        return "WebSocketMessage{type = " + type + ", text = " + text + "}";
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
