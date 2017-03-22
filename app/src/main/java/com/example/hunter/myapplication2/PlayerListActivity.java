package com.example.hunter.myapplication2;

import android.support.v4.app.Fragment;

/**
 * Created by Hunter on 3/22/2017.
 */

public class PlayerListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PlayerListFragment();
    }
}
