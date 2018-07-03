package montero.app_movil_lot5.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.MonsterAdapter;
import montero.app_movil_lot5.Models.Monster;
import montero.app_movil_lot5.R;

import static montero.app_movil_lot5.MainActivity.lot5Database;


/**
 * A simple {@link Fragment} subclass.
 */
public class BestiaryFragment extends Fragment {

    
    public BestiaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestiary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        final ListView listView = (ListView) getActivity().findViewById(R.id.monster_list);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Monster> monsters = lot5Database.daoMonster().fetchAllMonsters();
                if (monsters!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            MonsterAdapter fa = new MonsterAdapter(getContext(), (ArrayList<Monster>) monsters);
                            listView.setAdapter(fa);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                        long id) {

                                }
                            });
                        }
                    });

                }
            }
        }) .start();
    }
}
