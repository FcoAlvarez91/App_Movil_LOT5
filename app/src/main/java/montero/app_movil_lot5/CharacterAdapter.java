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

public class CharacterAdapter extends ArrayAdapter<Character> {

    private Context mContext;
    private List<Character> CharacterList;

    public CharacterAdapter(Context context, ArrayList<Character> list) {
        super(context, 0, list);
        mContext = context;
        CharacterList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listCharacter = convertView;
        if(listCharacter == null)
            listCharacter = LayoutInflater.from(mContext).inflate(R.layout.list_character, parent,false);

        Character currentForm = CharacterList.get(position);

        TextView name = (TextView) listCharacter.findViewById(R.id.character_name);
        name.setText(currentForm.name);

        TextView race = (TextView) listCharacter.findViewById(R.id.character_race);
        race.setText(currentForm.race);

        TextView arch = (TextView) listCharacter.findViewById(R.id.character_arch);
        arch.setText(currentForm.arch);

        String lvl = "" + currentForm.level;
        TextView level = (TextView) listCharacter.findViewById(R.id.character_level);
        level.setText(lvl);

        TextView role = (TextView) listCharacter.findViewById(R.id.character_role);
        role.setText(currentForm.role);

        return listCharacter;
    }

}