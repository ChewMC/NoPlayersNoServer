package pw.chew.noplayersnoserver;
import org.bukkit.Bukkit;
import java.util.Timer;
import java.util.TimerTask;

class StopIt extends TimerTask {
    public void run() {
      System.out.println("Server is gone.");
      Bukkit.getServer().shutdown();
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
    timer.schedule(task, 20000, 5000);
  }

  public void restartTimer() {
    timer.cancel();
    startTimer();
  }

  public void stopTimer() {
    timer.cancel();
  }
}
