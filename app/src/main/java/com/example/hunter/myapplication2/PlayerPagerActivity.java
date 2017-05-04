package com.example.hunter.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Hunter on 5/2/2017.
 */

public class PlayerPagerActivity extends AppCompatActivity {
    private static final String EXTRA_PLAYER_ID = "com.example.hunter.myapplication2.player_id";

    private ViewPager viewPager;
    private List<Player> players;

    public static Intent newIntent(Context packageContext, UUID playerId) {
        Intent intent = new Intent(packageContext, PlayerPagerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_pager);

        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);

        viewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        players = PlayerLab.get(this).getPlayers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Player player = players.get(position);
                return PlayerFragment.newInstance(player.getId());
            }

            @Override
            public int getCount() {
                return players.size();
            }
        });

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId().equals(playerId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
