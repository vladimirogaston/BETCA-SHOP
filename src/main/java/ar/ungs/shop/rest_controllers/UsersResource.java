package ar.ungs.shop.rest_controllers;

import ar.ungs.shop.bussines_controllers.UsersController;
import ar.ungs.shop.dtos.AuthUserDto;
import ar.ungs.shop.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(UsersResource.USUARIOS)
public class UsersResource {

    public static final String USUARIOS = "/usuarios";

    public static final String TOKEN = "/token";

    @Autowired
    private UsersController controller;

    @PostMapping(value = TOKEN)
    public String getToken(@RequestBody AuthUserDto auth){
        return controller.auth(auth);
    }

    @PostMapping
    public UserDto save(@Valid @RequestBody UserDto user) {
        return controller.save(user);
    }
}
