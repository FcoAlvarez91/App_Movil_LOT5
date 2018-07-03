package montero.app_movil_lot5.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import montero.app_movil_lot5.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonsterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonsterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonsterFragment extends Fragment {

    public MonsterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monster, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
}
