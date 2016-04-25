package pw.untamemadman.plugins.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Squeal extends Plugin
{
    String configLoaded = "false";
    String Prefix = "§8[§bAlert§8]§r";

    @Override
    public void onEnable()
    {
        defaultConfig();
        loadConfig();
        getProxy().getPluginManager().registerCommand(this, new SquealCommand("squeal"));
    }

    class SquealCommand extends Command
    {
        public SquealCommand(String name)
        {
            super(name);
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

    public void loadConfig()
    {
        try {
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            configLoaded = "true";
        }
        catch(IOException i)
        {
            getLogger().info("Squeal could not find its config file. Something must of gone wrong");
            getLogger().info("There is a chance the plugin will not function");
            getLogger().info("Error message: ");
            i.printStackTrace();
            configLoaded = "false";
        }
    }
    public void defaultConfig()
    {
        if (!getDataFolder().exists())
        {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if(!file.exists())
        {
            try (InputStream in = getResourceAsStream("config.yml"))
            {
                Files.copy(in, file.toPath());
            }
            catch (IOException ii)
            {
                getLogger().info("Squeal could not find its config file. Something must of gone wrong");
                getLogger().info("There is a chance the plugin will not function");
                getLogger().info("Error message: ");
                ii.printStackTrace();
                configLoaded = "false";
            }
        }
    }
}