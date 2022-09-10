package com.oxd.springbootmongodb.resource;

import com.oxd.springbootmongodb.domain.Post;
import com.oxd.springbootmongodb.services.PostServices;
import org.springframework.data.repository.config.RepositoryConfigurationSourceSupport;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostResource {

    final PostServices postServices;

    public PostResource(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam String text){
        return ResponseEntity.status(HttpStatus.OK).body(postServices.findByTitle(text));
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam String text, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDate){
        return ResponseEntity.status(HttpStatus.OK).body(postServices.fullSearch(text, minDate, maxDate));
    }
}
