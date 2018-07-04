package montero.app_movil_lot5.Adapters;

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

import montero.app_movil_lot5.Models.Ability;
import montero.app_movil_lot5.R;

public class AbilityAdapter extends ArrayAdapter<Ability> {

    private Context mContext;
    private List<Ability> AbilityList;

    public AbilityAdapter(Context context, ArrayList<Ability> list) {
        super(context, 0, list);
        mContext = context;
        AbilityList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listAbility = convertView;
        if(listAbility == null)
            listAbility = LayoutInflater.from(mContext).inflate(R.layout.list_ability, parent,false);

        Ability currentForm = AbilityList.get(position);

        TextView name = (TextView) listAbility.findViewById(R.id.ab_name);
        name.setText(currentForm.getName());

        TextView range = (TextView) listAbility.findViewById(R.id.ab_range);
        range.setText(currentForm.getRange());

        TextView effect = (TextView) listAbility.findViewById(R.id.ab_effect);
        effect.setText(currentForm.getEffect());

        TextView flair = (TextView) listAbility.findViewById(R.id.ab_flair);
        flair.setText(currentForm.getFlair());

        return listAbility;
    }

}