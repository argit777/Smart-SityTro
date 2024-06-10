package ru.smartcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smartcity.dto.ResponseResult;
import ru.smartcity.model.User;
import ru.smartcity.model.UserDetailsImpl;
import ru.smartcity.service.UserService;

@RestController
public class AuthController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseResult<User>> authenticate(Authentication authentication) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();
                User user = this.service.get(id);
                return new ResponseEntity<>(new ResponseResult<>(null, user), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseResult<>("Failed authentication", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}

