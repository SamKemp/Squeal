package pw.untamemadman.plugins.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by untamemadman on 24/04/2016.
 */
public class SquealCommand extends Command
{
    String Prefix = "§8[§6MultiCubeUK§8]§r ";

    public SquealCommand(String name)
    {
        super("squeal");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        String Arguments = "";


            //loop through all the arguments and save them to a string
            if (!(args.length == 0))
            {
                for (int i = 0; i < args.length; i++)
                {
                    String arg = args[i] + " ";
                    Arguments = Arguments + arg;
                }

                if (sender instanceof ProxiedPlayer)
                {
                    if(sender.hasPermission("squeal.command"))
                    {
                        //For every player on the BungeeCord
                        for (ProxiedPlayer receivers : ProxyServer.getInstance().getPlayers())
                        {
                            receivers.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + Arguments));
                        }
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&9You don't have the required permissions to run this command"));
                    }
                }
                else
                {
                    for (ProxiedPlayer receivers : ProxyServer.getInstance().getPlayers())
                    {
                        receivers.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + Arguments));
                    }
                }
            }
            else
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&9You didn't type a message to send to the network"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&9&lDumbass"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "Correct usage: &5/squeal <message>"));
            }
    }
}
