package pw.chew.noplayersnoserver;
import org.bukkit.Bukkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TimerUntilBye {
  static ScheduledExecutorService executor;
  static Future<?> shutdown;

  public TimerUntilBye() {
    executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void startTimer() {
    shutdown = executor.schedule(this::run, NoPlayersNoServer.time, TimeUnit.MILLISECONDS);
  }

  public void restartTimer() {
    stopTimer();
    startTimer();
  }

  public void stopTimer() {
    try {
      shutdown.cancel(true);
    } catch(NullPointerException e) {
      System.out.println("[NoPlayersNoServer] Tried to cancel already cancelled timer. It's ok though, ignore me and move on!");
    }
  }

  public void run() {
    if(anyOnlinePlayers()) {
      System.out.println("[NoPlayersNoServer] ??? There's players online, not gonna stop the server");
      shutdown.cancel(true);
    } else {
      System.out.println("[NoPlayersNoServer] The server will now shut down!");
      Bukkit.getServer().shutdown();
    }
  }

  public static boolean anyOnlinePlayers() {
    return Bukkit.getServer().getOnlinePlayers().size() > 0;
  }
}
