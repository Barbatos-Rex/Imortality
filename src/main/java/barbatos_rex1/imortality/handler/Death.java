package barbatos_rex1.imortality.handler;

import barbatos_rex1.imortality.config.DefaultProperty;
import barbatos_rex1.imortality.domain.Imortals;
import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Properties;

public class Death implements Listener {

    private final Imortals technos;
    private final Properties props;

    public Death(Imortals technos, Properties props) {
        this.technos = technos;
        this.props = props;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void doNotAllowDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player p) {
            if (p.getHealth() <= event.getDamage() && technos.imortals().contains(p.getUniqueId().toString()) && !event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                p.spawnParticle(Particle.TOTEM, p.getLocation(), 1);
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
                if (Boolean.parseBoolean(props.getProperty(DefaultProperty.REGEN_ABSORPTION.propName()))) {
                    p.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(60, 1));
                    p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(10, 5));
                }
                try {
                    p.setHealth(Double.parseDouble(props.getProperty(DefaultProperty.AFTER_HEALTH.propName())));
                } catch (NumberFormatException e) {
                    p.setHealth(2d);
                }
                event.setCancelled(true);
            }
        }
    }

//    @EventHandler
//    public void debug(PlayerLoginEvent event) {
//        technos.imortals().add(event.getPlayer().getUniqueId().toString());
//        Bukkit.getConsoleSender().sendMessage("Registered");
//    }


}
