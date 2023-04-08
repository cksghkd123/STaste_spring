package com.hwang.staste.controller;

import com.hwang.staste.model.entity.User;
import com.hwang.staste.model.entity.UserAbility;
import com.hwang.staste.repository.UserAbilityRepository;
import com.hwang.staste.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/userAbility/{username}")
    private UserAbility getUserAbility(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        System.out.println(username);
        System.out.println(user.getUserAbility());
        return user.getUserAbility();
    }

}
