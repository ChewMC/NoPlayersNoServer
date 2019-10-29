package pw.chew.noplayersnoserver;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ByeByePlayer implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // Code that happens when a player joins
  }

  @EventHandler
  public void onPlayerJoin(PlayerQuitEvent event) {
    // Code that happens when a player leaves
  }
}
