package com.cloudtern.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudtern.ratingsdataservice.models.Rating;
import com.cloudtern.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating("Test", 4);
	}
	
	
	@GetMapping("/users/{userId}")
	public UserRating getUserRatings(@PathVariable String userId) {
		
		List<Rating> ratings = Arrays.asList(
				new Rating("123", 4),
				new Rating("200", 3)
			);
		
		UserRating userRatings = new UserRating();
		userRatings.setUserRatings(ratings);
		
		return userRatings;
	}
	
}
