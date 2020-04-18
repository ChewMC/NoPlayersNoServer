package pw.chew.noplayersnoserver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class NoPlayersNoServer extends JavaPlugin {
  static int time = 0;
  public static TimerUntilBye timer;
  // Fired when plugin is first enabled
  public void onEnable() {
    FileConfiguration config = this.getConfig();
    config.addDefault("shutdownAfter", 20000);
    config.options().copyDefaults(true);
    saveConfig();
    time = config.getInt("shutdownAfter");
    timer = new TimerUntilBye();

    this.getCommand("npns").setExecutor(new MainCommand());
    getServer().getPluginManager().registerEvents(new ByeByePlayer(), this);
    getLogger().info("Timer won't activate until someone leaves.");

    this.getCommand("npns").setTabCompleter(new MainCommand());
  }
  // Fired when plugin is disabled
  public void onDisable() {
    // Stop timer
    timer.stopTimer();
  }

  public int getTime() {
    return time;
  }

  public TimerUntilBye timer() {
    return timer;
  }
}
