package com.cloudtern.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloudtern.movieinfoservice.models.Movie;
import com.cloudtern.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;

	@GetMapping("/{movieId}")
	public Movie getMovieIfo(@PathVariable String movieId) {
		
		
		  String uri = "https://api.tmdb.org/3/movie/"+movieId+"?api_key="+apiKey;
		  
		  MovieSummary movieSummary = restTemplate.getForObject(uri, MovieSummary.class);
		  
		  Movie movie = new Movie(movieId, movieSummary.getTitle());
		
		return movie;
	}
	
}
