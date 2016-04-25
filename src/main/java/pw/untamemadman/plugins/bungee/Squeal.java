package pw.untamemadman.plugins.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class Squeal extends Plugin
{

    @Override
    public void onEnable()
    {
        getProxy().getPluginManager().registerCommand(this, new SquealCommand("squeal"));
    }
}