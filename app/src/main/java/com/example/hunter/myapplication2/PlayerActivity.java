package com.example.hunter.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

public class PlayerActivity extends SingleFragmentActivity {

    final static String TAG = "PlayerActivity";
    private static final String EXTRA_PLAYER_ID = "com.example.hunter.myapplication2.player_id";

    public static Intent newIntent(Context packageContext, UUID playerId) {
        Intent intent = new Intent(packageContext, PlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }
    public static String getExtraPlayerId() {
        return EXTRA_PLAYER_ID;
    }

    @Override
    protected Fragment createFragment() {
        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);
        //return new PlayerFragment();
        return PlayerFragment.newInstance(playerId);
    }
//    protected Fragment startActivity() {
//        Log.d(TAG, "startActivity");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        Fragment fragment = createFragment();

        if (fragment == null) {
            fragment = createFragment(); //new PlayerFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
