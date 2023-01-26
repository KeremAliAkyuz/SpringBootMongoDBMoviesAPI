package com.keremali.movies.services;

import com.keremali.movies.Movie;
import com.keremali.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> oneMovie(String  imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
