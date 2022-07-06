package barbatos_rex1.imortality.commands;

import barbatos_rex1.imortality.domain.Imortals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Properties;

public class Register implements CommandExecutor {

    private final Properties props;
    private final Imortals imortals;

    public Register(Imortals imortals, Properties props) {
        this.imortals = imortals;
        this.props = props;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        try {
            String playerName = args[0];
            Player p = Bukkit.getPlayer(playerName);
            if (p == null) {
                sender.sendMessage(ChatColor.RED + "Wrong number of arguments.");
                return false;
            }

            if (imortals.imortals().contains(p.getUniqueId().toString())) {
                boolean s = imortals.imortals().remove(p.getUniqueId().toString());
                sender.sendMessage(ChatColor.AQUA + p.getDisplayName() + " in no longer" + ChatColor.RED + " Imortal");
            } else {
                imortals.imortals().add(p.getUniqueId().toString());
                sender.sendMessage(ChatColor.AQUA + p.getDisplayName() + " is " + ChatColor.RED + "Imortal");
            }
            imortals.save();
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage(ChatColor.RED + "Wrong number of arguments.");
            return false;
        }
    }
}
