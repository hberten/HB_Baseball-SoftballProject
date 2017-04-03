package com.example.hunter.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    private RecyclerView playerRecyclerView;
    private PlayerAdapter adapter;

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
            //holder.lastNameTextView.setText(player.getLastName());
            holder.bindPlayer(player);
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        playerRecyclerView = (RecyclerView) view.findViewById(R.id.player_recycler_view);
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();

        adapter = new PlayerAdapter(players);
        playerRecyclerView.setAdapter(adapter);
    }

    private class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Player player;
        private TextView lastNameTextView;
        private CheckBox pitcherCheckBox;
        private TextView dateTextView;

        public PlayerHolder(View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);
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
            Intent intent = PlayerActivity.newIntent(getActivity(), player.getId());
            startActivity(intent);
        }
    }
}