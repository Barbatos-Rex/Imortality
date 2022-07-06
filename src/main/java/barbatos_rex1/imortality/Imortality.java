package barbatos_rex1.imortality;

import barbatos_rex1.imortality.commands.Register;
import barbatos_rex1.imortality.commands.RegisterCompletion;
import barbatos_rex1.imortality.commands.ShowImortals;
import barbatos_rex1.imortality.config.PropertiesLoader;
import barbatos_rex1.imortality.domain.Imortals;
import barbatos_rex1.imortality.handler.Death;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;

public final class Imortality extends JavaPlugin {
    Imortals imortals;
    @Override
    public void onEnable() {
        // Plugin startup logic
        PropertiesLoader propLoader = new PropertiesLoader();
        Properties props = propLoader.load();
        imortals = new Imortals(props);
        Death d = new Death(imortals,props);
        Bukkit.getPluginManager().registerEvents(d,this);
        this.getCommand("imortal").setExecutor(new Register(imortals,props));
        this.getCommand("imortal").setTabCompleter(new RegisterCompletion());
        this.getCommand("imortals").setExecutor(new ShowImortals(imortals));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        imortals.save();
    }
}
