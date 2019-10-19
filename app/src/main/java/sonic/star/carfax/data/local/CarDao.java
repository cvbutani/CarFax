package sonic.star.carfax.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import sonic.star.carfax.data.model.CarListing;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Chirag on 2019-10-16 at 19:32
 * Project - CarFax
 */
@Dao
public interface CarDao {
    /**
     * Insert NBATeams in database
     * @param teams to be added
     */
    @Insert(onConflict = REPLACE)
    void addCar(List<CarListing> teams);

    /**
     * Get all teams
     * @return List of CarListing
     */
    @Query("SELECT * FROM CarListing")
    Maybe<List<CarListing>> getCarListings();

    @Query("SELECT * FROM carlisting where id=:id")
    Maybe<CarListing> getCarInfo(String id);

    @Query("DELETE FROM CarListing")
    void deleteCarInfo();
}
