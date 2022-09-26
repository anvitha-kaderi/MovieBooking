package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Optional<Movie> findByTitleAndLanguageAndGenreAndDuration(String title, String language, String genre, String duration);

    Optional<Object> findAllByTitle(String v);

    Optional<Object> findAllByLanguage(String v);

    Optional<Object> findAllByGenre(String v);

    Optional<Object> findAllByDuration(String v);
}
