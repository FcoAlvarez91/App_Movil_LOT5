package montero.app_movil_lot5.Models.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import montero.app_movil_lot5.Models.Monster;

@Dao
public interface DaoMonster {

    @Insert
    void insertOnlySingleMonster(Monster monsters);
    @Insert
    void insertMultipleMonsters(List<Monster> formList);
    @Query("SELECT * FROM Monster WHERE id = :id")
    Monster fetchOneMonsterbyMonsterId(int id);
    @Query("SELECT * FROM Monster WHERE name = :name")
    Monster fetchOneMonsterbyname(String name);
    @Update
    void updateMonster(Monster movies);
    @Delete
    void deleteMonster(Monster movies);
    @Query("DELETE FROM Monster")
    void nukeMonsters();
    @Query("SELECT * FROM Monster")
    List<Monster> fetchAllMonsters();
}