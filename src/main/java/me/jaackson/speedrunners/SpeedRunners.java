package me.jaackson.speedrunners;

import me.jaackson.speedrunners.game.manager.TeamManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(SpeedRunners.MOD_ID)
public class SpeedRunners
{
	public static final String MOD_ID = "speedrunners";

    public SpeedRunners() {
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::crashClient);

        SpeedRunnersConfig.init(ModLoadingContext.get());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void setup(FMLServerStartedEvent event)
    {
        if(SpeedRunnersConfig.INSTANCE.enabled.get()) {
            TeamManager.initTeams(event.getServer());
        }
    }

    @SubscribeEvent
    public void onEvent(RegisterCommandsEvent event) {
        //TODO: Implement commands
    }

    private void crashClient(FMLClientSetupEvent event) 
    {
        throw new UnsupportedOperationException("This mod is not meant to be run on the client, please start this on a dedicated server.");
    }
}