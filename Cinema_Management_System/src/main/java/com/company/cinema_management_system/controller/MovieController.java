package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/admin/movie/add")
    public ResponseEntity<Response> add(@Valid @RequestBody MovieDto movieDto){
        Response response=movieService.add(movieDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/admin/movie/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody MovieDto movieDto,@PathVariable Integer id){
        Response response=movieService.update(movieDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id){
        Response response=movieService.deleteById(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Optional<Movie>> getById(@PathVariable Integer id){
        Optional<Movie> movie=movieService.getById(id);
        return ResponseEntity.status(movie.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(movie);

    }
    @GetMapping("/movie/list")
    public ResponseEntity<List<Movie>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getListOfMovies());
    }
}
