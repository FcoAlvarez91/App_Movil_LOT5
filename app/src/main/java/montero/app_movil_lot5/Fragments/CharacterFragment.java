package montero.app_movil_lot5.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.Adapters.AbilityAdapter;
import montero.app_movil_lot5.Models.Ability;
import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.R;

import static montero.app_movil_lot5.MainActivity.characterID;
import static montero.app_movil_lot5.MainActivity.lot5Database;


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
        TextView hp = getActivity().findViewById(R.id.hp);
        TextView str = getActivity().findViewById(R.id.str);
        TextView vit = getActivity().findViewById(R.id.vit);
        TextView sma = getActivity().findViewById(R.id.sma);
        TextView dex = getActivity().findViewById(R.id.dex);
        TextView agi = getActivity().findViewById(R.id.agi);
        TextView wis = getActivity().findViewById(R.id.wis);
        TextView cha = getActivity().findViewById(R.id.cha);

        name.setText(character.getName());
        race.setText(character.getRace());
        arch.setText(character.getArch());
        role.setText(character.getRole());
        hp.setText(String.valueOf(character.getHp()));
        String lvl = "" + character.getLevel();
        level.setText(lvl);
        String e1 = "" + character.getExp();
        exp.setText(e1);
        str.setText("" + character.getStr());
        vit.setText("" + character.getVit());
        sma.setText("" + character.getSma());
        dex.setText("" + character.getDex());
        agi.setText("" + character.getAgi());
        wis.setText("" + character.getWis());
        cha.setText("" + character.getCha());

        characterID = character.getId();

        final ListView listView = (ListView) getActivity().findViewById(R.id.ability_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Ability> abilities = lot5Database.daoAbility().fetchCharacterAbilities(character.getId());
                if (abilities!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            AbilityAdapter fa = new AbilityAdapter(getContext(), (ArrayList<Ability>) abilities);
                            listView.setAdapter(fa);
                        }
                    });
                }
            }
        }) .start();
    }

}
