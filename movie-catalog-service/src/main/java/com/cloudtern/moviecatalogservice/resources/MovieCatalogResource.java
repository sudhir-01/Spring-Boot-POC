package com.cloudtern.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloudtern.moviecatalogservice.models.CatalogItem;
import com.cloudtern.moviecatalogservice.models.Movie;
import com.cloudtern.moviecatalogservice.models.Rating;
import com.cloudtern.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	//@Autowired
	//private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		List<CatalogItem> catalogItem = new ArrayList<>();
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);

		
		//for every rated movie, we are getting movie info by calling movie-info-service and then creating the catalog item
		for (Rating rating : ratings.getUserRatings()) {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			//Using Web Client(Reactive/Asynchronous) instead of Rest Client(Synchronous)
			/*
			Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movies/"+rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
			*/
			
			catalogItem.add(new CatalogItem(movie.getName(), "Test Description", rating.getRating()));
		}
		
		return catalogItem;

	}
	
	/*public List<CatalogItem> getFallbackCatalog(@PathVariable String userId){
		return Arrays.asList(new CatalogItem("No Movie", "", 0));
	}*/
	
}
