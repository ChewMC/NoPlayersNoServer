package pw.chew.noplayersnoserver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainCommand implements CommandExecutor, TabCompleter {
  // This method is called, when somebody uses our command
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(args.length == 0) {
      return mainResponse(sender);
    }

    String arg0 = args[0].toLowerCase();
    switch (arg0) {
      case "help":
        return helpResponse(sender);
      case "get":
        if(sender.hasPermission("npns.get")) {
          return this.handleGet(sender);
        } else {
          return missingPermissionResponse(sender, "npns.get");
        }
      case "start":
        if(sender.hasPermission("npns.start")) {
          return this.handleStart(sender);
        } else {
          return missingPermissionResponse(sender, "npns.start");
        }
      case "stop":
        if(sender.hasPermission("npns.stop")) {
          return this.handleStop(sender);
        } else {
          return missingPermissionResponse(sender, "npns.stop");
        }
      default:
        sender.sendMessage("Invalid sub-command! Need help? Try \"/npns help\"");
        return true;
    }
  }

  public boolean mainResponse(CommandSender sender) {
    sender.sendMessage("Welcome to NoPlayersNoServer!");
    sender.sendMessage("This plugin works automatically. Just leave and a set amount of milliseconds later the server will stop!");
    sender.sendMessage("The server is set to shutdown after " + NoPlayersNoServer.time + " milliseconds.");
    sender.sendMessage("See more commands with \"/npns help\"");
    return true;
  }

  public boolean helpResponse(CommandSender sender) {
    sender.sendMessage("NoPlayersNoServer Help");
    sender.sendMessage("/npns help - This");
    sender.sendMessage("/npns get - Get the remaining time left. Useless if you're in-game.");
    sender.sendMessage("/npns stop - Force-stop the timer. Useless if you're in-game.");
    return true;
  }

  public boolean handleGet(CommandSender sender) {
    int time = NoPlayersNoServer.timer.getTimeRemaining();
    sender.sendMessage("There are " + (NoPlayersNoServer.time + time) + " milliseconds left on the timer.");
    return true;
  }

  public boolean handleStart(CommandSender sender) {
    NoPlayersNoServer.timer.startTimer();
    sender.sendMessage("Timer started successfully.");
    return true;
  }

  public boolean handleStop(CommandSender sender) {
    NoPlayersNoServer.timer.stopTimer();
    sender.sendMessage("Timer stopped successfully.");
    return true;
  }

  public static boolean missingPermissionResponse(CommandSender sender, String missingo) {
    sender.sendMessage("You are missing the proper permission to run this command! You need: " + missingo);
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    List<String> completions = new ArrayList<>();
    List<String> commands = new ArrayList<>();

    if (args.length == 1) {
      commands.add("help");
      commands.add("get");
      commands.add("stop");
      commands.add("start");
      StringUtil.copyPartialMatches(args[0], commands, completions);
    }
    Collections.sort(completions);
    return completions;
  }
}
