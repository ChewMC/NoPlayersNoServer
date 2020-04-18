package pw.chew.noplayersnoserver;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ByeByePlayer implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // Code that happens when a player joins
    NoPlayersNoServer.timer.stopTimer();
    System.out.println("[NoPlayersNoServer] Player joined, the timer has stopped.");
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    // Code that happens when a player leaves
    int players = event.getPlayer().getServer().getOnlinePlayers().size() - 1;
    if (players == 0) {
      NoPlayersNoServer.timer.startTimer();
      System.out.println("[NoPlayersNoServer] No players online, timer will start.");
    } else {
      System.out.println("[NoPlayersNoServer] Player left, however there are " + players + " players online, not starting timer.");
    }
  }
}
