package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import montero.app_movil_lot5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicRulesFragment extends Fragment {


    public BasicRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTransaction fragmentTransaction =

        return inflater.inflate(R.layout.fragment_basic_rules, container, false);
    }

}
