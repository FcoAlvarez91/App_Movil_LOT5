package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import montero.app_movil_lot5.R;
import montero.app_movil_lot5.Models.Monster;
/**
 * A simple {@link Fragment} subclass.
 */
public class MonsterFragment extends Fragment {

    public Monster monster;

    public MonsterFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monster, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = getActivity().findViewById(R.id.name);
        TextView family = getActivity().findViewById(R.id.family);
        TextView ability = getActivity().findViewById(R.id.ability);
        TextView level = getActivity().findViewById(R.id.level);

        name.setText(monster.getName());
        family.setText(monster.getFamily());
        ability.setText(monster.getAbility());
        String lvl = "" + monster.getLvl();
        level.setText(lvl);
    }

}
