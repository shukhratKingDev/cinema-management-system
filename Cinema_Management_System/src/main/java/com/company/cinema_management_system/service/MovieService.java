package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;


import java.util.List;
import java.util.Optional;

public interface MovieService {
    Response add(MovieDto movieDto);

    Response update(MovieDto movieDto,Integer id);

    List<Movie> getListOfMovies();

    Optional<Movie> getById(Integer id);

    Response deleteById(Integer id);
}
