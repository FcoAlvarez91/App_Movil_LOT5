package montero.app_movil_lot5.Models.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import montero.app_movil_lot5.Models.Item;


public interface DaoItem {

    @Dao
    public interface DaoAccess {

        @Insert
        void insertOnlySingleItem(Item Items);
        @Insert
        void insertMultipleItems(List<Item> formList);
        @Query("SELECT * FROM Item WHERE id = :id")
        Item fetchOneItembyItemId(int id);
        @Update
        void updateItem(Item movies);
        @Delete
        void deleteItem(Item movies);
        @Query("DELETE FROM Item")
        void nukeItems();
        @Query("SELECT * FROM Item")
        List<Item> fetchAllItems();
    }
}
