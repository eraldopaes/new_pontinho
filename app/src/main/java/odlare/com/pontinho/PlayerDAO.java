package odlare.com.pontinho;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert
    void save(Player player);

    @Query("SELECT * FROM players")
    LiveData<List<Player>> findAll();

    @Query("DELETE FROM players")
    void deleteAll();

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);
}
