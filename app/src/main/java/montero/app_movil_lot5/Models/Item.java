package montero.app_movil_lot5.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Profile.class,
        parentColumns = "id",
        childColumns = "character_id"))
public class Item {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int id;
    private int character_id;
    public String name;
    public int size;
    public int date;
    public int dateMax;
    public String comment;

}