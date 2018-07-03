package montero.app_movil_lot5.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity
public class Monster {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type_creature;
    private int lvl;
    private String ability;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_creature() {
        return type_creature;
    }

    public void setType_creature(String type_creature) {
        this.type_creature = type_creature;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
