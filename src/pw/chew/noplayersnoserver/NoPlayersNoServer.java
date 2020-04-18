package pw.chew.noplayersnoserver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class NoPlayersNoServer extends JavaPlugin {
  static int time = 0;
  // Fired when plugin is first enabled
  TimerUntilBye timer = new TimerUntilBye();
  public void onEnable() {
    FileConfiguration config = this.getConfig();
    config.addDefault("shutdownAfter", 20000);
    config.options().copyDefaults(true);
    saveConfig();
    time = config.getInt("shutdownAfter");

    this.getCommand("npns").setExecutor(new MainCommand());
    getServer().getPluginManager().registerEvents(new ByeByePlayer(), this);
    getLogger().info("Timer won't activate until someone leaves.");
  }
  // Fired when plugin is disabled
  public void onDisable() {

  }

  public int getTime() {
    return time;
  }
}
