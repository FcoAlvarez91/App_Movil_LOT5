package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import montero.app_movil_lot5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterFragment extends Fragment {


    public NewCharacterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_character, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerRace = (Spinner)getActivity().findViewById(R.id.new_race);
        ArrayAdapter<String> spinnerRaceArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.race));
        spinnerRace.setAdapter(spinnerRaceArrayAdapter);

        Spinner spinnerArch = (Spinner)getActivity().findViewById(R.id.new_arch);
        ArrayAdapter<String> spinnerArchArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.arch));
        spinnerArch.setAdapter(spinnerArchArrayAdapter);


    }
}
