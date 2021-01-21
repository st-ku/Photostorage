package info.freeit.photostorage.controller;

import info.freeit.photostorage.model.RegistrationDto;
import info.freeit.photostorage.model.User;
import info.freeit.photostorage.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path =  "/", consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody @Valid RegistrationDto registrationDto, Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        User currentUser = userService.findUserById(principal.getEmail());
        currentUser.setPictureUrl(registrationDto.getPictureUrl());
        currentUser.setGivenName(registrationDto.getGivenName());
        currentUser.setFamilyName(registrationDto.getFamilyName());
        currentUser.setPhoneNumber(registrationDto.getPhoneNumber());
        return userService.updateUser(currentUser)
                ? new ResponseEntity<>(currentUser, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/")
    public User currentUser(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        return userService.findUserById(principal.getEmail());
    }

}
