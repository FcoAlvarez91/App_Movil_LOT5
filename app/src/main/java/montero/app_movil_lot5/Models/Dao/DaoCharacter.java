package montero.app_movil_lot5.Models.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import montero.app_movil_lot5.Models.Character;


public interface DaoCharacter {

    @Dao
    public interface DaoAccess {

        @Insert
        void insertOnlySingleCharacter(Character Characters);
        @Insert
        void insertMultipleCharacters(List<Character> formList);
        @Query("SELECT * FROM Character WHERE id = :id")
        Character fetchOneCharacterbyCharacterId(int id);
        @Update
        void updateCharacter(Character movies);
        @Delete
        void deleteCharacter(Character movies);
        @Query("DELETE FROM Character")
        void nukeCharacters();
        @Query("SELECT * FROM Character")
        List<Character> fetchAllCharacters();
    }
}
