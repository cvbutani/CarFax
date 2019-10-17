package sonic.star.carfax.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Chirag on 2019-10-16 at 19:33
 * Project - CarFax
 */
public abstract class CarFaxDatabase extends RoomDatabase {

    private static CarFaxDatabase INSTANCE;

    public abstract CarDao carDao();

    public static synchronized CarFaxDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CarFaxDatabase.class, "CarInfo.db")
                    .build();
        }
        return INSTANCE;
    }
}
