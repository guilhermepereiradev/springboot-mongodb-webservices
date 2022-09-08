package com.oxd.springbootmongodb.resource;

import com.oxd.springbootmongodb.domain.Post;
import com.oxd.springbootmongodb.services.PostServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

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
}
