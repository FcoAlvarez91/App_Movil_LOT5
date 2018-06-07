package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import montero.app_movil_lot5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RulesFragment extends Fragment {


    public RulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rules_frame,new BasicRulesFragment()).addToBackStack("MainActivity");
        ft.commit();

        return inflater.inflate(R.layout.fragment_rules, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navigation = getActivity().findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_basic:
                    ft.replace(R.id.rules_frame,new BasicRulesFragment()).addToBackStack("MainActivity");
                    ft.commit();

                    return true;

                case R.id.navigation_combat:

                    return true;

                case R.id.navigation_characters:

                    return true;

            }
            return false;
        }

    };
}
