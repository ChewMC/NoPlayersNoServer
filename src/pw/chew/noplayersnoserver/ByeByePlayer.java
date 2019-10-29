package pw.chew.noplayersnoserver;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ByeByePlayer implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // Code that happens when a player joins
    new TimerUntilBye().stopTimer();
    System.out.println("Player joined, the timer has stopped.");
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    // Code that happens when a player leaves
    int players = event.getPlayer().getServer().getOnlinePlayers().size() - 1;
    System.out.println("Player left, however there are " + players + " players online.");
    if (players == 0) {
      new TimerUntilBye().startTimer();
      System.out.println("No players online, timer will start.");
    }
  }
}
