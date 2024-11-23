package top.szzz666.XLink.onebotEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Sender {
    @SerializedName("user_id")
    private String userId;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("sex")
    private String sex;

    @SerializedName("age")
    private int age;

    @SerializedName("area")
    private String area;

    @SerializedName("card")
    private String card;

    @SerializedName("role")
    private String role;

    @SerializedName("level")
    private String level;

    @SerializedName("mark")
    private String mark;

    public Sender() {
    }

    public Sender(String userId, String nickname, String sex, int age, String area, String card, String role, String level, String mark) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.area = area;
        this.card = card;
        this.role = role;
        this.level = level;
        this.mark = mark;
    }






    /**
     * 获取
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取
     * @return card
     */
    public String getCard() {
        return card;
    }

    /**
     * 设置
     * @param card
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * 获取
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置
     * @param mark
     */
    public void setMark(String mark) {
        this.mark = mark;
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
}
