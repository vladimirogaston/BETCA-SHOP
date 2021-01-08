package ar.ungs.shop.bussines_controllers;

import ar.ungs.shop.dtos.AuthUserDto;
import ar.ungs.shop.dtos.UserDto;
import ar.ungs.shop.entities.UserEntity;
import ar.ungs.shop.repositories.UsersDao;
import ar.ungs.shop.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersControllerImpl implements UsersController {

    private UsersDao usersDao;

    private JwtService jwtService;

    public UsersControllerImpl() {
    }

    public String getJwtToken(AuthUserDto credentials) {
        UserEntity entity = usersDao.findByName(credentials.getName());
        String token = "";
        if(entity.getPassword().equals(credentials.getPassword())) {
            List<String> roles = entity.getRoles().stream().map(Enum::name).collect(Collectors.toList());
            token = jwtService.createToken(entity.getName(), roles);
        }
        return token;
    }

    @Autowired
    public void setUsersDao(UsersDao dao) {
        this.usersDao = dao;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }
}
