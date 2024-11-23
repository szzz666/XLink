package top.szzz666.XLink.webchat;

import cn.nukkit.utils.Config;

import static top.szzz666.XLink.XLinkMain.dataFolderPath;


public class WebChatAuth {
    //    private static PluginBase plugin;
//
//    // 添加构造函数
//    public WebChatAuth(PluginBase plugin) {
//        WebChatAuth.plugin = plugin;
//    }
    public static boolean webChatAuth(String WebChatUserStr) {
        String[] WebChatUser = WebChatUserStr.split("&");
        if (WebChatUser.length == 2) {
            String WebChatName = WebChatUser[0];
            String WebChatPwd = WebChatUser[1];
//        String dataFolderPath = plugin.getDataFolder().getPath();
            // 保存玩家信息到配置文件
            Config PlayerInfos = new Config(dataFolderPath + "/PlayerInfos.yml", Config.YAML);
            String Pwd = PlayerInfos.getString(WebChatName);
            PlayerInfos.save();
            return Pwd.equals(WebChatPwd);
        }
        return false;
    }
}
