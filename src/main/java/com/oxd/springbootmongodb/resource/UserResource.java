package com.oxd.springbootmongodb.resource;

import com.oxd.springbootmongodb.domain.User;
import com.oxd.springbootmongodb.dto.UserDTO;
import com.oxd.springbootmongodb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    final UserService userService;
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> users = userService.findAll();
        List<UserDTO> listDto = users.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findOne(@PathVariable String id){
        User user = userService.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO userDTO){
        User user = userService.fromDTO(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO user){
        User newUser = userService.fromDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(newUser, id));
    }
}
