package top.szzz666.XLink;


import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import top.szzz666.XLink.event.PlayerEvents;
import top.szzz666.XLink.websocket.InitWebSocketClient;
import top.szzz666.XLink.websocket.InitWebSocketServer;
import top.szzz666.XLink.websocket.InitWsc;

import java.util.ArrayList;

import static top.szzz666.XLink.webchat.SetPlayer.setwebchatpwdform;


public class XLinkMain extends PluginBase {
    public static String Version = "1.2.0";
    public static Server myserver;
    public static Plugin plugin;

    public static String PluginVersion;

    public static boolean IsParentServer;
    public static boolean UseWebSocket;
    public static int WsServerPort;
    public static String WsConnectURL;
    public static int WsClientReconnectTime;
    public static int WsServerDelayStartupTime;
    public static String WsPassword;
    public static String key;
    //    public static ArrayList<String> IpWhiteList;
    public static String ws_url;
    public static ArrayList<String> QQGroups;
    public static String QQChatMessage;

    public static boolean UseQQGroupChat;
    public static ArrayList<String> QQQueryCmd;
    public static String QueryServerMessage;
    public static boolean UsePlayerListening;
    public static boolean HideDefaultJoinMessage;
    public static boolean HideDefaultQuitMessage;
    public static boolean UseServerJoinMessaging;
    public static boolean UseServerQuitMessaging;
    public static String PlayerJoinMessage;
    public static String PlayerQuitMessage;
    public static String PlayerChatMessage;
    public static String dataFolderPath;


    public static TaskHandler QQ_wsc;
    public static TaskHandler wss;
    public static TaskHandler wsc;

    @Override
    public void onLoad() {
        //插件读取
        this.getLogger().info("XLink插件读取");
        this.getLogger().info(TextFormat.colorize('&', "__  ___    _      _   "));
        this.getLogger().info(TextFormat.colorize('&', "\\ \\/ / |  (_)_ _ | |__"));
        this.getLogger().info(TextFormat.colorize('&', " >  <| |__| | ' \\| / /"));
        this.getLogger().info(TextFormat.colorize('&', "/_/\\_\\____|_|_||_|_\\_\\"));


//        String[] arr = new String[]{"XLink", "1.2.0", "szzz666"};
//        for (String s : arr){
//            this.getLogger().info(s);
//        }
    }

    @Override
    public void onEnable() {
        dataFolderPath = getDataFolder().getPath();

        //插件启用
        this.getLogger().info("XLink插件启用");
        myserver = getServer();
        plugin = this;

        this.saveResource("Config.yml");
        Config MyConfig = new Config(getDataFolder() + "/Config.yml", Config.YAML);

        PluginVersion = MyConfig.getString("Version");
        IsParentServer = MyConfig.getBoolean("IsParentServer");
        UseWebSocket = MyConfig.getBoolean("UseWebSocket");
        WsServerPort = MyConfig.getInt("WsServerPort");
        WsConnectURL = MyConfig.getString("WsConnectURL");
        WsClientReconnectTime = MyConfig.getInt("WsClientReconnectTime");
        WsServerDelayStartupTime = MyConfig.getInt("WsServerDelayStartupTime");
        WsPassword = MyConfig.getString("Password");
        key = MyConfig.getString("key");
        ws_url = MyConfig.getString("QQws_url");
        QQGroups = (ArrayList<String>) MyConfig.get("QQGroups");
        QQChatMessage = MyConfig.getString("QQChatMessage");
        UseQQGroupChat = MyConfig.getBoolean("UseQQGroupChat");
        QQQueryCmd = (ArrayList<String>) MyConfig.get("QQQueryCmd");
        QueryServerMessage = MyConfig.getString("QueryServerMessage");
        UsePlayerListening = MyConfig.getBoolean("UsePlayerListening");
        HideDefaultJoinMessage = MyConfig.getBoolean("HideDefaultJoinMessage");
        UseServerQuitMessaging = MyConfig.getBoolean("UseServerQuitMessaging");
        UseServerJoinMessaging = MyConfig.getBoolean("UseServerJoinMessaging");
        HideDefaultQuitMessage = MyConfig.getBoolean("HideDefaultQuitMessage");
        PlayerJoinMessage = MyConfig.getString("PlayerJoinMessage");
        PlayerQuitMessage = MyConfig.getString("PlayerQuitMessage");
        PlayerChatMessage = MyConfig.getString("PlayerChatMessage");

        if (!PluginVersion.equals(Version)) {
            this.getLogger().info(TextFormat.colorize('&', "&c配置文件版本与插件不符,请删除配置文件"));
            myserver.shutdown();
        }

        //注册事件监听器
        if (UsePlayerListening) {
            this.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        }

        if (UseWebSocket) {
            if (IsParentServer) {
                wss = myserver.getScheduler().scheduleDelayedTask(this, new Task() {
                    @Override
                    public void onRun(int i) {
                        InitWebSocketServer.initWebSocketServer();
                    }
                }, WsServerDelayStartupTime,true);
            } else {
                wsc = myserver.getScheduler().scheduleAsyncTask(this, new AsyncTask() {
                    @Override
                    public void onRun() {
                        InitWebSocketClient.initWebSocketClient();
                    }
                });
            }
        }
        if (!ws_url.isEmpty()) {
            QQ_wsc = myserver.getScheduler().scheduleAsyncTask(this, new AsyncTask() {
                @Override
                public void onRun() {
                    InitWsc.initWsc();
                }
            });
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("swcp")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                setwebchatpwdform(player);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        if (QQ_wsc != null) {
            QQ_wsc.cancel();
            QQ_wsc.remove();
        }
        if (wss != null) {
            wss.cancel();
            QQ_wsc.remove();
        }
        if (wsc != null) {
            wsc.cancel();
            QQ_wsc.remove();
        }
        this.getLogger().info("XLink插件关闭");
    }
}
