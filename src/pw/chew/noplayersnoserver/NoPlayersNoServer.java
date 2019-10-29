package pw.chew.noplayersnoserver;
import org.bukkit.plugin.java.JavaPlugin;

public class NoPlayersNoServer extends JavaPlugin {
	// Fired when plugin is first enabled
    public void onEnable() {
      this.getCommand("npns").setExecutor(new HelpCommand());
      getServer().getPluginManager().registerEvents(new ByeByePlayer(), this);
    }
    // Fired when plugin is disabled
    public void onDisable() {

    }

}
