package dev.sosasosa.basements.chat;

import dev.sosasosa.basements.chat.files.CustomConfig;
import dev.sosasosa.basements.chat.files.EvilEntity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class TestHandler implements Listener, Serializable {

    private String welcomeMessage = "§2[Server] 끝없는 동대문구청의 서버에 온 것을 환영한다, ";

    private String gongji = "§2< 공지 > 230228 10:52PM\n" +
            "- line 1\n" +
            "- line 2\n" +
            "    line 2-1\n" +
            "- line 3\n" +
            "  line 4";

    public Material[] oreMaterial = {Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE, Material.LAPIS_ORE};
    public String[] oreString = {"석탄을", "철을", "금을", "레드스톤을", "다이아몬드를", "청금석을"};

    public Sound[] sounds = {Sound.ENTITY_BAT_DEATH,
                             Sound.ENTITY_GHAST_DEATH,
                             Sound.ENTITY_HORSE_DEATH,
                             Sound.ENTITY_WANDERING_TRADER_DEATH,
                             Sound.ENTITY_WITHER_DEATH,
                             Sound.ENTITY_DONKEY_DEATH};

    public static transient final long serialVersionUID = 6956599980641027263L;

    private int count = 0;

    @EventHandler
    public void mineOre(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        //getServer().broadcastMessage(ChatColor.GREEN + "[Test] You broke a " + type + " block!");

        int typeIdx = findMetarialArray(oreMaterial, type);
        if (typeIdx != -1) {
            Player player = event.getPlayer();
            Random random = new Random(System.currentTimeMillis());
            player.playSound(player.getLocation(), sounds[random.nextInt(sounds.length)], 10f, 1);
            getServer().broadcastMessage(ChatColor.GREEN + "[Server] 우리 §c" + event.getPlayer().getName() + "§a이(가) " + oreString[typeIdx] + " 캤어요! ㅠㅜ");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String one_line = welcomeMessage + "§4" + event.getPlayer().getName() + ".";
        getServer().broadcastMessage(one_line);

        count = CustomConfig.get().getInt("handler_killed");
        CustomConfig.get().addDefault("handler_killed", count);
    }

    @EventHandler
    public void getPlayerCmd(AsyncPlayerChatEvent event) {
        //Player player = event.getPlayer();

        String[] chat = event.getMessage().split(" ");

        if (chat[0].equals("수정")) {

            gongji = arrTostr(chat);
            getServer().broadcastMessage("§2[Server] " + event.getPlayer().getName() + "이(가) 공지를 수정했습니다. (" + (gongji.length() - chat[0].length() - 1) + "자 입력됨)");
        }

        else if (chat[0].equals("공지")) {
            getServer().broadcastMessage(gongji);
        }
    }

    // org.bukkit.event.entity.EntityDeathEvent

    @EventHandler
    public void entityKilled(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();

        if (entity.getKiller() != null) {
            entity.getKiller().sendMessage("You killed " + entity.getName() + "...ㅠ");

            if (entity.getName().equals("Zombie")) {
                entity.getKiller().sendMessage("Zombieㅠㅜㅜㅜㅜㅜ");
                count++;
                CustomConfig.save();
            }
        }
    }

    // Utils

    public String arrTostr(String[] chat) {
        String concat = "";

        for (int i = 1; i < chat.length; i++) {
            concat += chat[i];
        }

        return concat;
    }

    public static int findMetarialArray(Material[] a, Material b) {
        for (int i = 0; i < a.length; i++) {
            if (b.equals(a[i])) { return i; }
        }
        return -1;
    }
}
