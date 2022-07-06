package barbatos_rex1.imortality.commands;

import barbatos_rex1.imortality.domain.Imortals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class ShowImortals implements CommandExecutor {
    private final Imortals imortals;

    public ShowImortals(Imortals imortals) {
        this.imortals = imortals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Imortals:");
        for (String uuid : imortals.imortals()) {
            sender.sendMessage(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName());
        }
        return true;
    }
}
