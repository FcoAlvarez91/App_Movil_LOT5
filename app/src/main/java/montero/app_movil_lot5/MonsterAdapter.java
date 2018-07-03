package montero.app_movil_lot5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.Models.Monster;

public class MonsterAdapter extends ArrayAdapter<Monster> {

    private Context mContext;
    private List<Monster> MonsterList;

    public MonsterAdapter(Context context, ArrayList<Monster> list) {
        super(context, 0, list);
        mContext = context;
        MonsterList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listMonster = convertView;
        if(listMonster == null)
            listMonster = LayoutInflater.from(mContext).inflate(R.layout.list_monster, parent,false);

        Monster currentForm = MonsterList.get(position);

        TextView name = (TextView) listMonster.findViewById(R.id.monster_name);
        name.setText(currentForm.getName());

        TextView family = (TextView) listMonster.findViewById(R.id.monster_family);
        family.setText(currentForm.getFamily());

        String lvl = "" + currentForm.getLvl();
        TextView level = (TextView) listMonster.findViewById(R.id.monster_lvl);
        level.setText(lvl);

        return listMonster;
    }

}