package com.example.Libary_Management_System.service;

import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.exception.UserNotFoundException;
import com.example.Libary_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // checking the user if db is empty
    public boolean hasUsers() {
        if(userRepository.count() == 0) return false;
        return  true;
    }

    // save the user POST
    public ResponseEntity<Users> saveUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // update the user PUT
    public ResponseEntity<Users> updateUser(int id, Users user) {
        Optional<Users> existingUserOp = userRepository.findById(id);
        if(existingUserOp.isEmpty()) {
            throw new UserNotFoundException("User with mentioned id " + id + "is not present so can not be update");
        }
        Users existingUser = existingUserOp.get();
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        Users updatedUser = userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // DELETE BY ID
    public ResponseEntity<String> deleteUser(int id) {
        Optional<Users> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " is not found... It can'be Deleted");
        }

        userRepository.delete(existingUser.get());
        Optional<Users> deletedUser = userRepository.findById(id);
        if(deletedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not deleted..");
    }
}
