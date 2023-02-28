package dev.sosasosa.basements.chat.commands;

import dev.sosasosa.basements.chat.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(CustomConfig.get().getString("Zombie_killed"));
        }

        return true;
    }
}
