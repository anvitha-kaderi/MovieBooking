package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Optional<Movie> findByTitleAndLanguageAndGenreAndDuration(String title, String language, String genre, String duration);


    Optional<List<Movie>> findAllByTitle(String v);

    Optional<List<Movie>> findAllByLanguage(String v);

    Optional<List<Movie>> findAllByGenre(String v);

    Optional<List<Movie>> findAllByDuration(String v);
}
