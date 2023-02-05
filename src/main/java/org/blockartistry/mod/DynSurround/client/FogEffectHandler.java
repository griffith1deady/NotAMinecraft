package org.blockartistry.mod.DynSurround.client;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.optifine.Config;
import org.blockartistry.mod.DynSurround.ModOptions;
import org.blockartistry.mod.DynSurround.client.fog.*;
import org.blockartistry.mod.DynSurround.event.DiagnosticEvent;
import org.blockartistry.mod.DynSurround.util.Color;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class FogEffectHandler implements IClientEffectHandler {
    public boolean hasEvents() {
        return true;
    }

    private boolean doFog() {
        return (ModOptions.enableBiomeFog || ModOptions.allowDesertFog);
    }

    public void process(World world, EntityPlayer player) {
        if (doFog()) {
            this.fogRange.tick();
            this.fogColor.tick();
        }
    }

    protected HolisticFogColorCalculator fogColor = new HolisticFogColorCalculator();

    protected HolisticFogRangeCalculator fogRange = new HolisticFogRangeCalculator();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void fogColorEvent(EntityViewRenderEvent.FogColors event) {
        if (doFog()) {
            Material material = event.block.getMaterial();
            if (material != Material.lava && material != Material.water) {
                Color color = this.fogColor.calculate(event);
                if (color != null) {
                    event.red = color.red;
                    event.green = color.green;
                    event.blue = color.blue;
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogRenderEvent(EntityViewRenderEvent.RenderFogEvent event) {
        if (doFog()) {
            Material material = event.block.getMaterial();
            if (material != Material.lava && material != Material.water) {
                FogResult result = this.fogRange.calculate(event);
                if (result != null) {
                    GL11.glFogf(GL_FOG_START, result.getStart() * Config.getFogStart());
                    GL11.glFogf(GL_FOG_END, result.getEnd());
                }
            }
        }
    }

    @SubscribeEvent
    public void diagnostics(DiagnosticEvent.Gather event) {
        if (doFog()) {
            event.output.add("Fog Range: " + this.fogRange.toString());
            event.output.add("Fog Color: " + this.fogColor.toString());
        } else {
            event.output.add("FOG: IGNORED");
        }
    }

    @SubscribeEvent(receiveCanceled = false, priority = EventPriority.LOWEST)
    public void onWorldLoad(@Nonnull WorldEvent.Load event) {
        setupTheme(event.world);
    }

    protected void setupTheme(@Nonnull World world) {
        this.fogColor = new HolisticFogColorCalculator();
        this.fogRange = new HolisticFogRangeCalculator();
        if (ModOptions.enableBiomeFog) {
            this.fogColor.add(new BiomeFogColorCalculator());
            this.fogRange.add(new BiomeFogRangeCalculator());
        }
        if (ModOptions.enableElevationHaze)
            this.fogRange.add(new HazeFogRangeCalculator());
        if (ModOptions.enableMorningFog)
            this.fogRange.add(new MorningFogRangeCalculator());
        if (ModOptions.enableWeatherFog)
            this.fogRange.add(new WeatherFogRangeCalculator());
    }
}
