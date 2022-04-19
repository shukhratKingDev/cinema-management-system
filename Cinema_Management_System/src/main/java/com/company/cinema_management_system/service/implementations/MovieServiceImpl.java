package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Hall;
import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.repository.MovieRepository;
import com.company.cinema_management_system.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;


    @Override
    public Response add(MovieDto movieDto) {
boolean exists=movieRepository.existsByName(movieDto.getName());
if (exists){
    return new Response("This movie is already exists",false);
}
        Movie movie=new Movie();

movieRepository.save(parse(movieDto,movie));
return new Response("Movie saved successfully !!!",true);
    }

    @Override
    public Response update(MovieDto movieDto,Integer id) {
boolean exist=movieRepository.existsByNameAndIdNot(movieDto.getName(),id);
        if (!exist) {
            return new Response("Movie with this id not found",false);
        }
        Optional<Movie> movie = movieRepository.findById(id);
         movieRepository.save(parse(movieDto,movie.get()));
         return new Response("Movie successfully updated",true);

    }

    @Override
    public List<Movie> getListOfMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getById(Integer id) {
       return check(id);
    }

    @Override
    public Response deleteById(Integer id) {
       movieRepository.deleteById(id);
       return new Response("Movie deleted successfully !!!",true);

    }

    public Movie parse(MovieDto movieDto,Movie movie){
        movie.setName(movieDto.getName());
        movie.setNumberOfTicketsSold(movieDto.getNumberOfTicketsSold());
        movie.setDurationInMillis(movieDto.getDurationInMillis());
        movie.setBeginDate(movieDto.getBeginDate());
        movie.setSessionDate(movieDto.getSessionDate());
        Hall hall=new Hall();
        hall.setName(movieDto.getHall().getName());
        hall.setNumberOfRows(movieDto.getHall().getNumberOfRows());
        hall.setNumberOfSeatsInRow(movieDto.getHall().getNumberOfSeatsInRow());

        movie.setHall(hall);
        movie.setDescription(movieDto.getDescription());
        movie.setPrice(movieDto.getPrice());
        return movie;
    }

    public Optional<Movie> check(Integer id){
        Optional<Movie> movie=movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie;
    }
        return  null;
    }
}
