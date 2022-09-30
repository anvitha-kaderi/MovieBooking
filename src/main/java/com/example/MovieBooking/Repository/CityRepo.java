package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City,Integer> {
    Optional<City> findByCitynameAndState(String cityname, String state);

    @Modifying
    @Query("delete from City c where c.id=:id")
    void delete(@Param("id") int id);
}
