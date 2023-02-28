package dev.sosasosa.basements.chat;

import java.io.File;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.Random;
import java.util.Timer;

import dev.sosasosa.basements.chat.commands.Message;
import dev.sosasosa.basements.chat.commands.ReloadCommand;
import dev.sosasosa.basements.chat.files.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BasementsChat extends JavaPlugin {

    Logger logger = getServer().getLogger();
    Timer timerForAnnouncement = new Timer();
    Timer timerForWhisper = new Timer();

    @Override
    public void onEnable() {
        super.onEnable();
        logger.info("Plugin Activated!");

        getServer().getPluginManager().registerEvents(new TestHandler(), this);
        logger.info("TestHandler : Activated!");

        // defalut : 5000, 500 000
        timerForAnnouncement.schedule(new TimerTask() {
            @Override
            public void run() {
                sendAnnouncement();
            }
        }, 5000, 5000000);
        timerForWhisper.schedule(new TimerTask() {
            @Override
            public void run() {
                sendWhisper();
            }
        }, 5000, 5000000);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("Zombie_killed", 0);
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("zombie").setExecutor(new Message());
        getCommand("preload").setExecutor(new ReloadCommand());
    }

    @Override
    public void onLoad() {
        logger.info("PLUG-IN : onLoad()");
    }

    @Override
    public void onDisable() {
        logger.info("Plugin Inactivated!");
    }

    /**
     * Gong-ji for you.
     */
    public void sendAnnouncement() {
        getServer().broadcastMessage("""
                §2< 공지 > 230224 06:08PM
                - line 1
                - line 2
                  line 2-1
                - line 3
                  * line 3-1
                    §fcommand : '수정 {something}, 공지'
                """);
        /*
        Random random = new Random(System.currentTimeMillis());
        logger.info("PLUG-IN : get number '" + random + "'.");
        switch (random.nextInt(3)) {
            case 0 -> getServer().broadcastMessage("§0Message -1-");
            case 1 -> getServer().broadcastMessage("§1Message -2-");
            case 2 -> getServer().broadcastMessage("§2Message -3-");
            default -> getServer().broadcastMessage("§fUnpredictable Number");
        }
         */
    }

    /**
     * Bring your ears.
     */
    public void sendWhisper() {
        List<Player> playerList = (List<Player>)getServer().getOnlinePlayers();

        if (playerList.size() == 0) {
            return;
        }

        logger.info("PLUG-IN : playerList.size() : " + playerList.size());

        Random random = new Random(System.currentTimeMillis());

        int playerNumber = random.nextInt(playerList.size());
        playerList.get(playerNumber).sendMessage("야.");
    }

    // File

    /*
    private void loadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
        try {
            if (!file.exists()) {
                logger.info("LOAD CONFIG!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                config.save(file);
            }
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
