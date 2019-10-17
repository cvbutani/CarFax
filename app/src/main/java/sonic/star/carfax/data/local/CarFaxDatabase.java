package sonic.star.carfax.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import sonic.star.carfax.data.model.CarListing;

/**
 * Created by Chirag on 2019-10-16 at 19:33
 * Project - CarFax
 */
@Database(entities = {CarListing.class}, exportSchema = false, version = 1)
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
