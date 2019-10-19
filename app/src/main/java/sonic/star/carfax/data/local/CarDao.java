package sonic.star.carfax.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import sonic.star.carfax.data.model.CarListing;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Chirag on 2019-10-16 at 19:32
 * Project - CarFax
 */
@Dao
public interface CarDao {
    /**
     * Insert car list in database
     * @param list to be added
     */
    @Insert(onConflict = REPLACE)
    void addCar(List<CarListing> list);

    /**
     * Get all car list
     * @return List of CarListing
     */
    @Query("SELECT * FROM CarListing")
    Maybe<List<CarListing>> getCarListings();

    /**
     * Get one car info based on id
     * @param id - car id
     * @return - car detail based on id
     */
    @Query("SELECT * FROM carlisting where id=:id")
    Maybe<CarListing> getCarInfo(String id);

    /**
     * Delete carListing table info
     */
    @Query("DELETE FROM CarListing")
    void deleteCarInfo();
}
