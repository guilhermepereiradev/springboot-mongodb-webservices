package com.oxd.springbootmongodb.services;

import com.oxd.springbootmongodb.domain.User;
import com.oxd.springbootmongodb.repositories.UserRepository;
import com.oxd.springbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByid(String id){
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow( () -> {
            throw new ObjectNotFoundException("Objeto não encontrado!");
        });
    }
}
