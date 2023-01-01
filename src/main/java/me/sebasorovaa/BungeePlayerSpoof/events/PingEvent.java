package me.sebasorovaa.BungeePlayerSpoof.events;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.Random;

import static me.sebasorovaa.BungeePlayerSpoof.Main.fakepla;

public class PingEvent implements Listener {
    public static int mincount = 1;
    public static int maxcount = 1;
    int changeit = 0;
    public void changeitfunc() {
        Random changerandom = new Random();
        int coinFlip2 = changerandom.nextInt(100);
        if (coinFlip2 >= 5) {
            changeit = 0;
        } else {
            changeit = 1;
        }
    }

    public void countchange() {
        Random random = new Random();
///        int minimumRealisticNumber = -1;
//        int maximumRealisticNumber = 25;
        int coinFlip = random.nextInt(100);
        int countChange = 1;
        if (coinFlip >= 50){
            fakepla = fakepla + countChange;
        } else {
            fakepla = fakepla - countChange;
        }
        if (fakepla < mincount){
            fakepla = mincount;
        }
        if (fakepla > maxcount) {
            fakepla = maxcount;
        }

        if (fakepla < 2) {
            fakepla = 3;
        }
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        changeitfunc();
        if (changeit == 0) {
            ServerPing ping = event.getResponse();
            ServerPing.Players current = ping.getPlayers();
            ping.setPlayers(new ServerPing.Players(current.getMax(), fakepla, current.getSample()));
            event.setResponse(ping);
        }
        else {
            countchange();
            ServerPing ping = event.getResponse();
            ServerPing.Players current = ping.getPlayers();
            ping.setPlayers(new ServerPing.Players(current.getMax(), fakepla, current.getSample()));
            event.setResponse(ping);
        }
    }
}
