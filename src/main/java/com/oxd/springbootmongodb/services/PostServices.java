package com.oxd.springbootmongodb.services;

import com.oxd.springbootmongodb.domain.Post;
import com.oxd.springbootmongodb.repositories.PostRepository;
import com.oxd.springbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    final
    PostRepository postRepository;

    public PostServices(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(()-> {
            throw new ObjectNotFoundException("Objeto não encontrado!");
        });
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
