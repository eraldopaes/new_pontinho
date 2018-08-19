package odlare.com.pontinho;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Player.class}, version = 1)
public abstract class PlayerRoomDatabase extends RoomDatabase {

    private static PlayerRoomDatabase INSTANCE;

    public static PlayerRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    return Room.databaseBuilder(context, PlayerRoomDatabase.class, "pontinho").build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract PlayerDAO playerDAO();
}
