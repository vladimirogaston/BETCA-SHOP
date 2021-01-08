package ar.ungs.shop.rest_controllers;

import ar.ungs.shop.bussines_controllers.UsersController;
import ar.ungs.shop.dtos.AuthUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import ar.ungs.shop.services.JwtService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(JwtResource.JWTS)
public class JwtResource {

    public static final String JWTS = "/jwts";

    public static final String TOKEN = "/token";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersController controller;

    @PreAuthorize("authenticated")
    @PostMapping(value = TOKEN)
    public String login(@AuthenticationPrincipal User activeUser) {
        List<String> roleList = activeUser.getAuthorities().stream().map
                (authority -> authority.getAuthority()).collect(Collectors.toList());
        return jwtService.createToken(activeUser.getUsername(), roleList);
    }

    @PostMapping
    public String getToken(@RequestBody AuthUserDto auth){
        return controller.getJwtToken(auth);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String verify() {
        return "OK. permitido JWT";
    }
}
