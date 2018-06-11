package montero.app_movil_lot5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import montero.app_movil_lot5.Character;
import montero.app_movil_lot5.Profile;
import montero.app_movil_lot5.R;
import montero.app_movil_lot5.fragments.ProfileFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }
}
