package pw.chew.noplayersnoserver;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
  // This method is called, when somebody uses our command
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Server server = sender.getServer();
    int players = server.getOnlinePlayers().size();

    sender.sendMessage("Welcome to NPNS!\nThis plugin works automatically. Just leave and a set amount of seconds later the server will stop!\nCurrently there are " + players + " player(s) online!\nThe server is set to shutdown after " + NoPlayersNoServer.time + " milliseconds.");

    // If the player (or console) uses our command correct, we can return true
    return true;
  }
}
