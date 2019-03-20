package io.javabraims.controller;

import io.javabraims.model.CatalogItem;
import io.javabraims.model.Movie;
import io.javabraims.model.UserRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 38066 on 19.03.2019.
 */
@Slf4j
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        log.info("INTO getCatalog() with ID: {}", userId);
        UserRating ratings = restTemplate.getForObject("http://movie-data-service/ratingdata/users/" + userId,
                UserRating.class);
        return ratings.getRatingList().stream().map(rating -> {
            // For each movieId, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            // Put them all together
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        })
                .collect(Collectors.toList());
    }

    /** Movie movie = webClientBuilder.build()
     .get()
     .uri("http://localhost:8081/movies/" + rating.getMovieId())
     .retrieve()
     .bodyToMono(Movie.class)
     .block();*/
}
