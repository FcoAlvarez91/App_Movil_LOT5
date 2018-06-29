package montero.app_movil_lot5.Models.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import montero.app_movil_lot5.Models.Ability;


public interface DaoAbility {

    @Dao
    public interface DaoAccess {

        @Insert
        void insertOnlySingleAbility(Ability Abilitys);
        @Insert
        void insertMultipleAbilitys(List<Ability> formList);
        @Query("SELECT * FROM Ability WHERE id = :id")
        Ability fetchOneAbilitybyAbilityId(int id);
        @Update
        void updateAbility(Ability movies);
        @Delete
        void deleteAbility(Ability movies);
        @Query("DELETE FROM Ability")
        void nukeAbilitys();
        @Query("SELECT * FROM Ability")
        List<Ability> fetchAllAbilitys();
    }
}
