package pw.chew.noplayersnoserver;
import org.bukkit.Bukkit;

import java.util.concurrent.*;

public class TimerUntilBye {
  static ScheduledExecutorService executor;
  static Future<?> shutdown;
  static long endsAt = 0;

  public TimerUntilBye() {
    executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void startTimer() {
    shutdown = executor.schedule(this::run, NoPlayersNoServer.time, TimeUnit.MILLISECONDS);
    endsAt = System.nanoTime() + (NoPlayersNoServer.time * 1000000);
  }

  public void restartTimer() {
    stopTimer();
    startTimer();
  }

  public int getTimeRemaining() {
    if(endsAt == 0 || shutdown.isCancelled()) {
      return NoPlayersNoServer.time;
    } else {
      return (int) ((endsAt - System.nanoTime()) / 1000000);
    }
  }

  public void stopTimer() {
    try {
      shutdown.cancel(true);
      endsAt = 0;
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
