package montero.app_movil_lot5.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.Adapters.CharacterAdapter;
import montero.app_movil_lot5.R;

import static montero.app_movil_lot5.MainActivity.lot5Database;
import static montero.app_movil_lot5.MainActivity.profileID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

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

        final ListView listView = (ListView) getActivity().findViewById(R.id.character_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Character> characters = lot5Database.daoCharacter().fetchProfileCharacters(profileID);
                if (characters!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            CharacterAdapter fa = new CharacterAdapter(getContext(), (ArrayList<Character>) characters);
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
                    });
                }
            }
        }) .start();
    }
}
