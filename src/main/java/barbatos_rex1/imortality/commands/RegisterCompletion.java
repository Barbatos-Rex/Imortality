package barbatos_rex1.imortality.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.LinkedList;
import java.util.List;

public class RegisterCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> results = new LinkedList<>();
        List<String> players = new LinkedList<>();
        Bukkit.getServer().getOnlinePlayers().forEach(p -> players.add(p.getName()));
        players.sort(String::compareTo);
        StringUtil.copyPartialMatches(args[0], players, results);
        return results;
    }


}
