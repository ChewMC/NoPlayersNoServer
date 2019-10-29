package pw.chew.noplayersnoserver;
import org.bukkit.Bukkit;
import java.util.Timer;
import java.util.TimerTask;

class StopIt extends TimerTask {
  public void run() {
    if(anyOnlinePlayers()) {
      System.out.println("[NoPlayersNoServer] ??? There's players online, not gonna stop the server");
      TimerUntilBye.timer.cancel();
    } else {
      System.out.println("[NoPlayersNoServer] The server will now shut down!");
      Bukkit.getServer().shutdown();
    }
  }

  public static boolean anyOnlinePlayers() {
    return Bukkit.getServer().getOnlinePlayers().size() > 0;
  }
}

public class TimerUntilBye {
  static Timer timer;
  static TimerTask task;

  public TimerUntilBye() {
    timer = new Timer();
    task = new StopIt();
  }

  public void startTimer() {
    timer.schedule(task, NoPlayersNoServer.time, 5000);
  }

  public void restartTimer() {
    stopTimer();
    startTimer();
  }

  public void stopTimer() {
    try {
      timer.cancel();
    } catch(IllegalStateException e) {
      System.out.println("[NoPlayersNoServer] Tried to cancel already cancelled timer. It's ok though, ignore me and move on!.");
    }
    timer.purge();
  }
}
