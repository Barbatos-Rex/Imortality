package barbatos_rex1.imortality.config;

import org.bukkit.Bukkit;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_PATH = "./config/Imortality.properties";

    private Properties props;

    public PropertiesLoader() {
        props = System.getProperties();
        props.setProperty(DefaultProperty.FILE_NAME.propName(), DefaultProperty.FILE_NAME.value());
        props.setProperty(DefaultProperty.EXTENSION.propName(), DefaultProperty.EXTENSION.value());
        props.setProperty(DefaultProperty.PERSIST_DIRECTORY.propName(), DefaultProperty.PERSIST_DIRECTORY.value());
        props.setProperty(DefaultProperty.AFTER_HEALTH.propName(), DefaultProperty.AFTER_HEALTH.value());
        props.setProperty(DefaultProperty.PERSISTENT.propName(), DefaultProperty.PERSISTENT.value());
        props.setProperty(DefaultProperty.REGEN_ABSORPTION.propName(), DefaultProperty.REGEN_ABSORPTION.value());
    }


    public Properties load() {
        try (FileReader fr = new FileReader(CONFIG_PATH)) {
            props.load(fr);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().info("Creating config file in" + CONFIG_PATH + ". Using default properties!");
            genConfigFile();
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not read properties from config file. Using default properties!");
            genConfigFile();
        }
        return props;
    }

    private void genConfigFile() {
        new File(CONFIG_PATH).getParentFile().mkdirs();
        StringBuilder sb = new StringBuilder();
        sb.append("#Configuration file for Imortality plugin").append("\n");
        sb.append("\n").append("#This property is referent to the name of the file created to store imortal players. This file SHOULD NOT CONTAIN ANY dot or usage of extensions").append("\n");
        sb.append(DefaultProperty.FILE_NAME.propName()).append("=").append(DefaultProperty.FILE_NAME.value()).append("\n");
        sb.append("\n").append("#This property referes to the extension of the persistent file.").append("\n");
        sb.append(DefaultProperty.EXTENSION.propName()).append("=").append(DefaultProperty.EXTENSION.value()).append("\n");
        sb.append("\n").append("#This property value is the path (either relative to the server folder, either absolute) to the persistent.").append("\n");
        sb.append(DefaultProperty.PERSIST_DIRECTORY.propName()).append("=").append(DefaultProperty.PERSIST_DIRECTORY.value());
        sb.append("\n#NOTE: Changing the name or directory of persistence units can be useful for having multiple backups stored in the system ready to be loaded in case of a complicated shutdown").append("\n\n");
        sb.append("\n").append("#This property value toogles regen and absorption").append("\n");
        sb.append("#").append(DefaultProperty.REGEN_ABSORPTION.propName()).append("=").append("FALSE").append("\n");
        sb.append(DefaultProperty.REGEN_ABSORPTION.propName()).append("=").append(DefaultProperty.REGEN_ABSORPTION.value()).append("\n");
        sb.append("\n").append("#This property is the number of MINUTES between backup files, if 0 then there will be no backups. The number of minutes must be a whole number. This property is very useful for large\n#important servers that may be in risk of any acident or in case of any undesired consequence of the market, restoring it to a fixed point.").append("\n");
        sb.append(DefaultProperty.AFTER_HEALTH.propName()).append("=").append(DefaultProperty.AFTER_HEALTH.value()).append("\n");
        sb.append("\n").append("#This property defines if the players will be stored nad loaded with each reset or not").append("\n");
        sb.append(DefaultProperty.PERSISTENT.propName()).append("=").append(DefaultProperty.PERSISTENT.value()).append("\n");
        sb.append("#").append(DefaultProperty.PERSISTENT.propName()).append("=").append("FALSE").append("\n");
        try (PrintWriter pw = new PrintWriter(new FileWriter(CONFIG_PATH), true)) {
            pw.println(sb);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not create the config file for Imortality");
        }
    }


}
