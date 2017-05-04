package com.example.hunter.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hunter on 3/22/2017.
 */

public class PlayerListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView playerRecyclerView;
    private PlayerAdapter adapter;
    private boolean subtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        playerRecyclerView = (RecyclerView) view.findViewById(R.id.player_recycler_view);
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null) {
            subtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_player_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (subtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_player:
                Player player = new Player();
                PlayerLab.get(getActivity()).addPlayer(player);
                Intent intent = PlayerPagerActivity.newIntent(getActivity(), player.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                subtitleVisible = !subtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        PlayerLab playerLab = PlayerLab.get(getActivity());
        int playerCount = playerLab.getPlayers().size();
        String subtitle = getString(R.string.subtitle_format, playerCount);

        if (!subtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();

        if (adapter == null) {
            adapter = new PlayerAdapter(players);
            playerRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Player player;
        private TextView lastNameTextView;
        private CheckBox pitcherCheckBox;
        private TextView dateTextView;

        public PlayerHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            lastNameTextView = (TextView) itemView.findViewById(R.id.list_item_player_textView);
            pitcherCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_player_pitcher_checkBox);
            dateTextView = (TextView) itemView.findViewById(R.id.list_item_date_textView);
        }

        public void bindPlayer(Player player) {
            this.player = player;
            lastNameTextView.setText(player.getLastName());
            pitcherCheckBox.setChecked(player.isPitcher());
            //dateTextView.setChecked(player.get());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), player.getLastName(), Toast.LENGTH_SHORT).show();
            Intent intent = PlayerPagerActivity.newIntent(getActivity(), player.getId());
//            Intent intent = new Intent(getActivity(), PlayerActivity.class);
            startActivity(intent);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {
        private List<Player> players;

        public PlayerAdapter(List<Player> players) {
            this.players = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_player, parent, false);
            return new PlayerHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            Player player = players.get(position);
            holder.bindPlayer(player);
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}