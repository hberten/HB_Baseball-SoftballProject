package com.example.hunter.myapplication2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment {
    private Player player;

    private Button dateButton;

    private EditText lastNameField;
    private EditText firstNameField;

    CheckBox pitcherCheckBox;
    CheckBox catcherCheckBox;
    CheckBox infieldCheckBox;
    CheckBox outfieldCheckBox;

    private static final String TAG = "PlayerActivity";
    private static final String ARG_PLAYER_ID = "player_id";

    private OnFragmentInteractionListener listener;

    public PlayerFragment() {
        // Required empty public constructor
    }

    public static PlayerFragment newInstance(UUID playerID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER_ID, playerID);

        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID playerId = (UUID) getArguments().getSerializable(ARG_PLAYER_ID);
        player = PlayerLab.get(getActivity()).getPlayer(playerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container,false);
        lastNameField = (EditText) v.findViewById(R.id.last_name_textbox);
        lastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player.setLastName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        firstNameField = (EditText) v.findViewById(R.id.first_name_textbox);
        firstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player.setFirstName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dateButton = (Button) v.findViewById(R.id.date_button);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        dateButton.setText(dateFormat.format(new Date()));
        dateButton.setEnabled(false);

        pitcherCheckBox = (CheckBox) v.findViewById(R.id.player_pitcher_checkbox);
        pitcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttownView, boolean isChecked) {
                Log.d(TAG, "Pitcher set to " + isChecked);
                player.setPitcher(isChecked);
            }
        });
        catcherCheckBox = (CheckBox) v.findViewById(R.id.player_catcher_checkbox);
        catcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttownView, boolean isChecked) {
                Log.d(TAG, "Catcher set to " + isChecked);
                player.setCatcher(isChecked);
            }
        });
        infieldCheckBox = (CheckBox) v.findViewById(R.id.player_infield_checkbox);
        infieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttownView, boolean isChecked) {
                Log.d(TAG, "Infield set to " + isChecked);
                player.setInfield(isChecked);
            }
        });
        outfieldCheckBox = (CheckBox) v.findViewById(R.id.player_outfield_checkbox);
        outfieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttownView, boolean isChecked) {
                Log.d(TAG, "Outfield set to " + isChecked);
                player.setOutfield(isChecked);
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            listener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
