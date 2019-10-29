package pw.chew.noplayersnoserver;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
          Server server = sender.getServer();
          int players = server.getOnlinePlayers().size();

          Player p = (Player) sender;
          p.sendMessage("Welcome to NPNS!\nThis plugin works automatically. Just leave and 10 seconds later the server will stop!\nCurrently there are " + players + " player(s) online");
      }

      // If the player (or console) uses our command correct, we can return true
      return true;
    }
}
