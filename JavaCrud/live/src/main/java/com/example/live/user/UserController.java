package com.example.live.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public User getUserById(@RequestParam Long id) 
    {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createUser(@RequestBody User u)
    {
        return userRepository.save(u);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User u)
    {
        User existingUser = userRepository.findById(id).get();
        existingUser.setName(u.getName());
        existingUser.setEmail(u.getEmail());
        return userRepository.save(existingUser);
    }

    @DeleteMapping
    public String deleteUser(@PathVariable Long id)
    {
        try {
            userRepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "User not found";
        }
    }
}
