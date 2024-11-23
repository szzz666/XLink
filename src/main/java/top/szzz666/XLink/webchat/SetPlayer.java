package top.szzz666.XLink.webchat;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.handler.FormResponseHandler;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.Config;


import static top.szzz666.XLink.XLinkMain.dataFolderPath;


public class SetPlayer {

    // 添加PluginBase属性
//    private static PluginBase plugin;
//
//    // 添加构造函数
//    public SetPlayer(PluginBase plugin) {
//        SetPlayer.plugin = plugin;
//    }

    public static void setPlayer(Player player, String inputpassword) {
        // 获取数据文件夹路径
//        dataFolderPath = plugin.getDataFolder().getPath();
        // 保存玩家信息到配置文件
        Config PlayerInfos = new Config(dataFolderPath + "/PlayerInfos.yml", Config.YAML);
        PlayerInfos.set(player.getName(),inputpassword);
        PlayerInfos.save();
    }



    public static void setwebchatpwdform(Player player) {
        FormWindowCustom form = new FormWindowCustom("设置官网聊天密码");
        // 添加组件
        form.addElement(new ElementInput("", "输入要设置的密码"));
        form.addElement(new ElementInput("", "再一次输入要设置的密码"));
        // 设置提交操作
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            String inputpassword = form.getResponse().getInputResponse(0); // 获取文本输入框的值
            String againpassword = form.getResponse().getInputResponse(1); // 获取下拉框选择的文本
            if (inputpassword.equals(againpassword)){
                player.sendMessage("成功设置密码为："+inputpassword);
                setPlayer(player,inputpassword);
            }else {
                player.sendMessage("§c两次密码不一致");
            }
            // 处理用户提交的数据
        }));
        // 显示表单给玩家
        player.showFormWindow(form);
    }
}
