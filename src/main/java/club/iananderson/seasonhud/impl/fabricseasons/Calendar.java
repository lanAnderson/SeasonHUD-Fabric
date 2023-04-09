package club.iananderson.seasonhud.impl.fabricseasons;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import net.minecraft.world.item.Item;


import java.util.List;

import static club.iananderson.seasonhud.config.ModConfig.*;


public class Calendar {
    public static boolean invCalendar;

    public static boolean curiosLoaded() {
        return FabricLoader.getInstance().isModLoaded("trinkets");
    }

    public static Item calendar = Registry.ITEM.get(new ResourceLocation("seasons","season_calendar"));
    public static boolean calendar() {
        if (INSTANCE.needCalendar) {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (player != null) {
                Inventory inv = player.getInventory();
                int slot = findCalendar(inv, calendar); //+ findCuriosCalendar(player,calendar);

                invCalendar = (slot >= 0);

            }

        }
        else invCalendar = true;

        return invCalendar;
    }

    private static int findCalendar(Inventory inv, Item item) {
        for (int i = 0; i < inv.items.size(); ++i) {
            if ((!inv.items.get(i).isEmpty() && inv.items.get(i).is(item))
                    || (!inv.offhand.get(0).isEmpty() && inv.offhand.get(0).is(item))) {
                return i;
            }
        }
        return -1;
    }

//    private static int findCuriosCalendar(Player player, Item item) {
//        if (curiosLoaded()) {
//            List<SlotResult> findCalendar = CuriosApi.getCuriosHelper().findCurios(player, item);
//            return findCalendar.size();
//        }
//        else return 0;
//    }
}


