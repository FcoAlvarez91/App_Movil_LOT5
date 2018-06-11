package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import montero.app_movil_lot5.Character;
import montero.app_movil_lot5.CharacterAdapter;
import montero.app_movil_lot5.Profile;
import montero.app_movil_lot5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public Profile profile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView username = (TextView)getActivity().findViewById(R.id.profile_username);
        username.setText(profile.username);

        if (profile.characters!=null) {
            final ListView listView = (ListView) getActivity().findViewById(R.id.character_list);
            CharacterAdapter fa = new CharacterAdapter(getContext(), profile.characters);
            listView.setAdapter(fa);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                    Character char2 = (Character) listView.getItemAtPosition(position);
                    CharacterFragment cf = new CharacterFragment();
                    cf.character = char2;
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, cf).addToBackStack("MainActivity");
                    ft.commit();

                }
            });
        }
        else{

        }
    }
}
