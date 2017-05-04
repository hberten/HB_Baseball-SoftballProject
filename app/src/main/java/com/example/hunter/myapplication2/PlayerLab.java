package com.example.hunter.myapplication2;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hunter on 3/22/2017.
 */

public class PlayerLab {
    private List<Player> players;
    public static String TAG = "PlayerLab";

    private static PlayerLab playerLab;
    private PlayerLab(Context context) {
        players = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Player player = new Player();
//            player.setLastName("Jacobs " + i + (char) (0x2600 + (int) (Math.random()*255)));
//            Log.d(TAG, player.getLastName());
//            player.setPitcher(i%2==0);
//            players.add(player);
//        }
    }
    public void addPlayer(Player p) {
        players.add(p);
    }

    public static PlayerLab get(Context context) {
        if (playerLab == null)
            playerLab = new PlayerLab(context);
        return playerLab;
    }
    public Player getPlayer(UUID id) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
