package montero.app_movil_lot5.Models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import montero.app_movil_lot5.Models.Dao.DaoAbility;
import montero.app_movil_lot5.Models.Dao.DaoCharacter;
import montero.app_movil_lot5.Models.Dao.DaoItem;
import montero.app_movil_lot5.Models.Dao.DaoProfile;

@Database(entities = {Profile.class, Character.class, Ability.class, Item.class}, version = 1, exportSchema = false)
public abstract class Lot5Database extends RoomDatabase {
    public abstract DaoProfile daoProfile();
    public abstract DaoCharacter daoCharacter();
    public abstract DaoAbility daoAbility();
    public abstract DaoItem daoItem();
}