package barbatos_rex1.imortality.domain;

import barbatos_rex1.imortality.config.DefaultProperty;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class Imortals {

    private Set<String> imortals;
    private final String saveFile;


    public Imortals(Properties props) {
        saveFile=props.getProperty(DefaultProperty.PERSIST_DIRECTORY.propName())+"/"+props.getProperty(DefaultProperty.FILE_NAME.propName())+"."+props.getProperty(DefaultProperty.EXTENSION.propName());
        load();
    }


    public Set<String> imortals() {
        return imortals;
    }



    private void load(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))){
            imortals = ((Set<String>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            imortals=new LinkedHashSet<>();
        }catch (NullPointerException e){
            try {
                new File(saveFile).createNewFile();
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))){
         oos.writeObject(imortals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
