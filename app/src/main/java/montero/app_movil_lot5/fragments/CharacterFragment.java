package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment {

    public Character character;

    public CharacterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = getActivity().findViewById(R.id.name);
        TextView race = getActivity().findViewById(R.id.race);
        TextView arch = getActivity().findViewById(R.id.arch);
        TextView role = getActivity().findViewById(R.id.role);
        TextView level = getActivity().findViewById(R.id.level);
        TextView exp = getActivity().findViewById(R.id.exp);
        TextView skills = getActivity().findViewById(R.id.skills);
        TextView hp = getActivity().findViewById(R.id.hp);

        character.buildCharacter();
        name.setText(character.name);
        race.setText(character.race);
        arch.setText(character.arch);
        role.setText(character.role);
        hp.setText(String.valueOf(character.hp));
        skills.setText(Arrays.toString(character.skills));
        String lvl = "" + character.level;
        level.setText(lvl);
        String e1 = "" + character.exp;
        exp.setText(e1);
    }

}
