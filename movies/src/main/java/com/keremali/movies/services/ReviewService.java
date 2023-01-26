package com.keremali.movies.services;

import com.keremali.movies.Movie;
import com.keremali.movies.Review;
import com.keremali.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;//repositoryden başka bir db'yle iletişim kurma yoludur
    //kompleks işlemlerde kullanılır
    public Review createReview(String reviewBody,String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));//insert içine pushladığın datayı döner

        mongoTemplate.update(Movie.class)//hangi class'ı update etmek istiyoruz
                .matching(Criteria.where("imdbId").is(imdbId))//sahip olduğumuz imdbId ile depodaki eşleşiyor mu
                .apply(new Update().push("reviewIds").value(review))//Movie sınıfındaki reviewIds kısmına reviewi ekle
                .first();//tek bir film aldığından ve bunu güncellediğinden emin olmak için

        return review;
    }
}
