package com.keremali.movies.controllers;

import com.keremali.movies.Review;
import com.keremali.movies.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){//Request Body ile post ettiğimiz reviewi key string ve value string olarak map etmek istiyoruz bu mapi de payload olarak adlandırdık
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
        //postmandan gönderilen json isteği payload içindeki getlerle (payload.get("reviewBody")) ile tam olarak uyuşmalıdır.
    }
}
